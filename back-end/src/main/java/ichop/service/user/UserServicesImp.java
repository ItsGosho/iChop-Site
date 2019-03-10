package ichop.service.user;

import ichop.components.email.EmailServices;
import ichop.domain.entities.tokens.PasswordResetToken;
import ichop.domain.entities.users.User;
import ichop.domain.entities.users.UserRole;
import ichop.domain.models.binding.user.UserRegisterBindingModel;
import ichop.domain.models.binding.user.UserForgottenPasswordBindingModel;
import ichop.domain.models.binding.user.UserResetPasswordBindingModel;
import ichop.domain.models.service.token.PasswordResetTokenServiceModel;
import ichop.domain.models.service.user.UserRoleServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.token.TokenNotValidException;
import ichop.exceptions.user.*;
import ichop.service.role.UserRoleServices;
import ichop.domain.entities.users.UserRoles;
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
import java.util.stream.Collectors;

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

            throw new UsernameNotFoundException("");
        }

        UserServiceModel userServiceModel = this.userCrudServices.getUserByUsername(usernameOrEmail);

        if (userServiceModel != null) {
            User user = this.modelMapper.map(userServiceModel, User.class);
            return user;
        }

        throw new UsernameNotFoundException("");
    }

    @Override
    public UserServiceModel register(UserRegisterBindingModel userRegisterBindingModel) {

        if (this.userCrudServices.existsByUsername(userRegisterBindingModel.getUsername())) {
            throw new UserAlreadyExistsException();
        }

        if (this.userCrudServices.existsByEmail(userRegisterBindingModel.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            throw new UserPasswordNotValidException();
        }

        User user = this.modelMapper.map(userRegisterBindingModel, User.class);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        user.setRegistrationDate(LocalDateTime.now());

        Set<UserRole> initialAuthorities = this.getInitialAuthorities().
                stream().
                map(x -> this.modelMapper.map(x, UserRole.class))
                .collect(Collectors.toSet());

        user.setAuthorities(initialAuthorities);

        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
        this.userCrudServices.save(userServiceModel);

        return userServiceModel;
    }

    private Set<UserRoleServiceModel> getInitialAuthorities() {
        Set<UserRoleServiceModel> userRoles = new HashSet<>();

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

        PasswordResetTokenServiceModel passwordResetTokenServiceModel = this.passwordResetTokenServices.createToken(this.modelMapper.map(user, UserServiceModel.class));
        PasswordResetToken createdToken = this.modelMapper.map(passwordResetTokenServiceModel, PasswordResetToken.class);

        this.emailServices.sendResetPasswordEmail(user.getEmail(), createdToken.getToken(), createdToken.getExpiryDate());
    }

    @Override
    public void resetPassword(UserResetPasswordBindingModel userResetPasswordBindingModel, String resetToken) {

        if (!this.passwordResetTokenServices.isValid(resetToken)) {
            throw new TokenNotValidException();
        }

        if (!userResetPasswordBindingModel.getPassword().equals(userResetPasswordBindingModel.getConfirmPassword())) {
            throw new UserPasswordNotValidException();
        }

        PasswordResetTokenServiceModel passwordResetTokenServiceModel = this.passwordResetTokenCrudServices.getTokenByToken(resetToken);
        UserServiceModel userServiceModel = passwordResetTokenServiceModel.getUser();

        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.passwordEncoder.encode(userResetPasswordBindingModel.getPassword()));

        userServiceModel = this.modelMapper.map(user, UserServiceModel.class);

        this.userCrudServices.save(userServiceModel);
        this.passwordResetTokenServices.deleteOldestToken(userServiceModel);
    }

    @Override
    public void follow(UserServiceModel user, UserServiceModel userToFollow) {

        if (user == null || userToFollow == null) {
            throw new UserNotFoundException();
        }

        boolean isUserAlreadyFollowingHim = this.userCrudServices.isUserAlreadyFollowedUser(user, userToFollow);

        if(isUserAlreadyFollowingHim){
            throw new UserAlreadyFollowingHimException();
        }

        user.getFollowers().add(userToFollow);

        this.userCrudServices.save(user);

    }

    @Override
    public void unfollow(UserServiceModel user, UserServiceModel userToUnfollow) {

        if (user == null || userToUnfollow == null) {
            throw new UserNotFoundException();
        }

        boolean isEvenUserFollowingHim = this.userCrudServices.isUserAlreadyFollowedUser(user,userToUnfollow);

        if(!isEvenUserFollowingHim){
            throw new UserNotFollowingHimException();
        }

        user.getFollowers().removeIf(x->x.getId().equals(userToUnfollow.getId()));

        this.userCrudServices.save(user);

    }
}
