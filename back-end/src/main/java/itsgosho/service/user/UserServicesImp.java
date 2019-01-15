package itsgosho.service.user;

import itsgosho.components.email.EmailServices;
import itsgosho.domain.entities.tokens.PasswordResetToken;
import itsgosho.domain.entities.users.User;
import itsgosho.domain.entities.users.UserRole;
import itsgosho.domain.models.binding.UserRegisterBindingModel;
import itsgosho.domain.models.binding.UserForgottenPasswordBindingModel;
import itsgosho.domain.models.binding.UserResetPasswordBindingModel;
import itsgosho.repository.user.UserRepository;
import itsgosho.service.role.UserRoleServices;
import itsgosho.service.role.UserRoles;
import itsgosho.service.token.PasswordResetTokenServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServicesImp implements UserServices {

    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+\\=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private final UserRepository userRepository;

    private final UserRoleServices userRoleServices;
    private final PasswordResetTokenServices passwordResetTokenServices;

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailServices emailServices;

    @Autowired
    public UserServicesImp(UserRepository userRepository, UserRoleServices userRoleServices, PasswordResetTokenServices passwordResetTokenServices, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder, EmailServices emailServices) {
        this.userRepository = userRepository;
        this.userRoleServices = userRoleServices;
        this.passwordResetTokenServices = passwordResetTokenServices;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.emailServices = emailServices;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        if(this.isLoginByEmail(usernameOrEmail)){
            return this.userRepository.findUserByEmail(usernameOrEmail.toLowerCase());
        }
        return this.userRepository.findUserByUsername(usernameOrEmail.toLowerCase());
    }

    private boolean isLoginByEmail(String usernameOrEmail){
        Matcher matcher = Pattern.compile(EMAIL_PATTERN)
                .matcher(usernameOrEmail);
        if(matcher.find()){
            return true;
        }
        return false;
    }

    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {

        if(this.existsByUsername(userRegisterBindingModel.getUsername().toLowerCase())){
            return false;
        }

        if(this.existsByEmail(userRegisterBindingModel.getEmail().toLowerCase())){
            return false;
        }

        User user = this.modelMapper.map(userRegisterBindingModel,User.class);
        user.setUsername(user.getUsername().toLowerCase());
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        user.setRegistrationDate(LocalDateTime.now());

        user.setAuthorities(this.getInitialAuthorities());

        this.userRepository.save(user);

        return true;
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.userRepository.findUserByUsername(username)!=null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.findUserByEmail(email)!=null;
    }

    private Integer getTotalUsers(){
        return this.userRepository.findAll().size();
    }

    private Set<UserRole> getInitialAuthorities(){
        Set<UserRole> userRoles = new HashSet<>();

        if(this.getTotalUsers()==0){
            userRoles.add(this.userRoleServices.create(UserRoles.OWNER));
            userRoles.add(this.userRoleServices.create(UserRoles.ADMIN));
            userRoles.add(this.userRoleServices.create(UserRoles.MODERATOR));
            userRoles.add(this.userRoleServices.create(UserRoles.USER));
        }else{
            userRoles.add(this.userRoleServices.create(UserRoles.USER));
        }
        return userRoles;
    }

    @Override
    public UserRole getRole(User user){
        if(user==null){
            return null;
        }
       return (UserRole) user
               .getAuthorities()
               .stream()
               .min((x1, x2) -> Integer.compare(UserRoles.valueOf(((GrantedAuthority) x2).getAuthority()).ordinal(), UserRoles.valueOf(((GrantedAuthority) x1).getAuthority()).ordinal()))
               .orElse(null);
    }

    @Override
    public boolean sendPasswordResetEmail(UserForgottenPasswordBindingModel userForgottenPasswordBindingModel) {

        User user = (User) this.loadUserByUsername(userForgottenPasswordBindingModel.getUsernameOrEmail());

        if(user==null){
           return false;
        }

        PasswordResetToken passwordResetToken = this.passwordResetTokenServices.createToken(user);

        if(passwordResetToken==null){
            return false;
        }

        String message = "You have 24h to reset your password: "+"localhost:8000/reset/password?token="+passwordResetToken.getToken();
        this.emailServices.sendSimpleMessage(user.getEmail(),"Reset password",message);

        return true;
    }

    @Override
    public boolean resetPassword(UserResetPasswordBindingModel userResetPasswordBindingModel, String resetToken) {

        if(!this.passwordResetTokenServices.isValid(resetToken)){
            return false;
        }

        User user = this.passwordResetTokenServices.getTokenByToken(resetToken).getUser();

        user.setPassword(this.passwordEncoder.encode(userResetPasswordBindingModel.getPassword()));

        this.userRepository.save(user);
        this.passwordResetTokenServices.deleteToken(user);
        return true;
    }
}
