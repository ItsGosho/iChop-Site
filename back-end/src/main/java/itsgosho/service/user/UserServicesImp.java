package itsgosho.service.user;

import itsgosho.components.email.EmailServices;
import itsgosho.domain.entities.tokens.PasswordResetToken;
import itsgosho.domain.entities.users.User;
import itsgosho.domain.entities.users.UserRole;
import itsgosho.domain.models.binding.user.UserRegisterBindingModel;
import itsgosho.domain.models.binding.user.UserForgottenPasswordBindingModel;
import itsgosho.domain.models.binding.user.UserResetPasswordBindingModel;
import itsgosho.exceptions.token.TokenCannotBeNullException;
import itsgosho.exceptions.token.TokenNotValidException;
import itsgosho.exceptions.user.UserCannotBeNullException;
import itsgosho.exceptions.user.UserNotFoundException;
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
        if (this.isEmail(usernameOrEmail)) {
            return this.userRepository.findUserByEmail(usernameOrEmail);
        }
        return this.userRepository.findUserByUsername(usernameOrEmail);
    }

    @Override
    public boolean isEmail(String value) {
        Matcher matcher = Pattern.compile(EMAIL_PATTERN)
                .matcher(value);
        return matcher.find();
    }

    @Override
    public User register(UserRegisterBindingModel userRegisterBindingModel) {

        if (this.existsByUsername(userRegisterBindingModel.getUsername())) {
            throw new UserNotFoundException();
        }

        if (this.existsByEmail(userRegisterBindingModel.getEmail())) {
            throw new UserNotFoundException();
        }

        User user = this.modelMapper.map(userRegisterBindingModel, User.class);
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        user.setRegistrationDate(LocalDateTime.now());

        user.setAuthorities(this.getInitialAuthorities());

        this.userRepository.save(user);

        return user;
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.userRepository.findUserByUsername(username) != null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.findUserByEmail(email) != null;
    }

    private Integer getTotalUsers() {
        return this.userRepository.findAll().size();
    }

    private Set<UserRole> getInitialAuthorities() {
        Set<UserRole> userRoles = new HashSet<>();

        if (this.getTotalUsers() == 0) {
            userRoles.add(this.userRoleServices.create(UserRoles.OWNER));
            userRoles.add(this.userRoleServices.create(UserRoles.ADMIN));
            userRoles.add(this.userRoleServices.create(UserRoles.MODERATOR));
            userRoles.add(this.userRoleServices.create(UserRoles.USER));
        } else {
            userRoles.add(this.userRoleServices.create(UserRoles.USER));
        }
        return userRoles;
    }

    @Override
    public UserRole getRole(User user) {

        if (user == null) {
            throw new UserCannotBeNullException();
        }

        return (UserRole) user
                .getAuthorities()
                .stream()
                .min((x1, x2) -> Integer.compare(UserRoles.valueOf(((GrantedAuthority) x2).getAuthority()).ordinal(), UserRoles.valueOf(((GrantedAuthority) x1).getAuthority()).ordinal()))
                .orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email);
    }

    @Override
    public void sendPasswordResetEmail(UserForgottenPasswordBindingModel userForgottenPasswordBindingModel) {

        User user = (User) this.loadUserByUsername(userForgottenPasswordBindingModel.getUsernameOrEmail());

        if (user == null) {
            throw new UserCannotBeNullException();
        }

        PasswordResetToken passwordResetToken = this.passwordResetTokenServices.createToken(user);

        if (passwordResetToken == null) {
            throw new TokenCannotBeNullException();
        }

        this.emailServices.sendResetPasswordEmail(user.getEmail(),passwordResetToken.getToken(),passwordResetToken.getExpiryDate());
    }

    @Override
    public void resetPassword(UserResetPasswordBindingModel userResetPasswordBindingModel, String resetToken) {

        if (!this.passwordResetTokenServices.isValid(resetToken)) {
            throw new TokenNotValidException();
        }

        User user = this.passwordResetTokenServices.getTokenByToken(resetToken).getUser();

        user.setPassword(this.passwordEncoder.encode(userResetPasswordBindingModel.getPassword()));

        this.userRepository.save(user);
        this.passwordResetTokenServices.deleteOldestToken(user);
    }
}
