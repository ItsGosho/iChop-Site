package ichop.service.user;

import ichop.components.email.EmailServices;
import ichop.domain.entities.tokens.PasswordResetToken;
import ichop.domain.entities.users.User;
import ichop.domain.entities.users.UserRole;
import ichop.domain.models.binding.user.UserRegisterBindingModel;
import ichop.domain.models.binding.user.UserForgottenPasswordBindingModel;
import ichop.domain.models.binding.user.UserResetPasswordBindingModel;
import ichop.domain.models.service.PasswordResetTokenServiceModel;
import ichop.domain.models.service.UserServiceModel;
import ichop.exceptions.token.TokenException;
import ichop.exceptions.token.TokenExceptionMessages;
import ichop.exceptions.user.UserException;
import ichop.exceptions.user.UserExceptionMessages;
import ichop.service.role.UserRoleServices;
import ichop.service.role.UserRoles;
import ichop.service.token.PasswordResetTokenServices;
import ichop.service.token.crud.PasswordResetTokenCrudServices;
import ichop.service.user.crud.UserCrudServices;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServicesImp implements UserServices {

    private final UserRoleServices userRoleServices;
    private final UserCrudServices userCrudServices;

    private final EmailServices emailServices;

    private final PasswordResetTokenServices passwordResetTokenServices;
    private final PasswordResetTokenCrudServices passwordResetTokenCrudServices;

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServicesImp(UserRoleServices userRoleServices, UserCrudServices userCrudServices, EmailServices emailServices, PasswordResetTokenServices passwordResetTokenServices, PasswordResetTokenCrudServices passwordResetTokenCrudServices, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRoleServices = userRoleServices;
        this.userCrudServices = userCrudServices;
        this.emailServices = emailServices;
        this.passwordResetTokenServices = passwordResetTokenServices;
        this.passwordResetTokenCrudServices = passwordResetTokenCrudServices;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        if (this.userCrudServices.isEmail(usernameOrEmail)) {

            UserServiceModel userServiceModel = this.userCrudServices.getUserByEmail(usernameOrEmail);

            if (userServiceModel != null) {
                User user = this.modelMapper.map(userServiceModel, User.class);
                return user;
            }

            throw new UsernameNotFoundException(UserExceptionMessages.USER_WITH_THAT_EMAIL_NOT_FOUND.getDescription());
        }

        UserServiceModel userServiceModel = this.userCrudServices.getUserByUsername(usernameOrEmail);

        if (userServiceModel != null) {
            User user = this.modelMapper.map(userServiceModel, User.class);
            return user;
        }

        throw new UsernameNotFoundException(UserExceptionMessages.USER_WITH_THAT_USERNAME_NOT_FOUND.getDescription());
    }

    @Override
    public UserServiceModel register(UserRegisterBindingModel userRegisterBindingModel) {

        if (this.userCrudServices.existsByUsername(userRegisterBindingModel.getUsername())) {
            throw new UserException(UserExceptionMessages.USERNAME_ALREADY_EXISTS);
        }

        if (this.userCrudServices.existsByEmail(userRegisterBindingModel.getEmail())) {
            throw new UserException(UserExceptionMessages.EMAIL_ALREADY_EXISTS);
        }

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            throw new UserException(UserExceptionMessages.PASSWORDS_DOESNT_MATCH);
        }

        User user = this.modelMapper.map(userRegisterBindingModel, User.class);
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        user.setRegistrationDate(LocalDateTime.now());

        user.setAuthorities(this.getInitialAuthorities());

        UserServiceModel userServiceModel = this.modelMapper.map(user,UserServiceModel.class);
        this.userCrudServices.save(userServiceModel);

        return userServiceModel;
    }

    private Set<UserRole> getInitialAuthorities() {
        Set<UserRole> userRoles = new HashSet<>();

        if (this.userCrudServices.getTotalUsers() == 0) {
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
    public void sendPasswordResetEmail(UserForgottenPasswordBindingModel userForgottenPasswordBindingModel) {

        User user = (User) this.loadUserByUsername(userForgottenPasswordBindingModel.getUsernameOrEmail());

        PasswordResetTokenServiceModel passwordResetTokenServiceModel = this.passwordResetTokenServices.createToken(this.modelMapper.map(user,UserServiceModel.class));
        PasswordResetToken passwordResetToken = this.modelMapper.map(passwordResetTokenServiceModel,PasswordResetToken.class);

        if (passwordResetToken == null) {
            throw new TokenException(TokenExceptionMessages.TOKEN_IS_NULL);
        }

        this.emailServices.sendResetPasswordEmail(user.getEmail(), passwordResetToken.getToken(), passwordResetToken.getExpiryDate());
    }

    @Override
    public void resetPassword(UserResetPasswordBindingModel userResetPasswordBindingModel, String resetToken) {

        if (!this.passwordResetTokenServices.isValid(resetToken)) {
            throw new TokenException(TokenExceptionMessages.TOKEN_IS_NOT_VALID);
        }

        if (!userResetPasswordBindingModel.getPassword().equals(userResetPasswordBindingModel.getConfirmPassword())) {
            throw new UserException(UserExceptionMessages.PASSWORDS_DOESNT_MATCH);
        }

        UserServiceModel userServiceModel = this.passwordResetTokenCrudServices.getTokenByToken(resetToken).getUser();

        User user = this.modelMapper.map(userServiceModel,User.class);
        user.setPassword(this.passwordEncoder.encode(userResetPasswordBindingModel.getPassword()));

        userServiceModel = this.modelMapper.map(user,UserServiceModel.class);

        this.userCrudServices.save(userServiceModel);
        this.passwordResetTokenServices.deleteOldestToken(userServiceModel);
    }
}
