package ichop.service.user;

import ichop.components.email.EmailServices;
import ichop.domain.entities.users.User;
import ichop.domain.entities.users.UserRoles;
import ichop.domain.models.binding.user.UserForgottenPasswordBindingModel;
import ichop.domain.models.binding.user.UserRegisterBindingModel;
import ichop.domain.models.binding.user.UserResetPasswordBindingModel;
import ichop.domain.models.service.role.UserRoleServiceModel;
import ichop.domain.models.service.token.PasswordResetTokenServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.role.UserRoleNotFoundException;
import ichop.exceptions.token.TokenNotValidException;
import ichop.exceptions.user.*;
import ichop.service.role.UserRoleServices;
import ichop.service.token.PasswordResetTokenServices;
import ichop.service.token.crud.PasswordResetTokenCrudServices;
import ichop.service.user.crud.UserCrudServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
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

            UserServiceModel foundedUser = this.userCrudServices.getUserByEmail(usernameOrEmail);

            if (foundedUser != null) {
                return this.modelMapper.map(foundedUser, User.class);
            }

            throw new UsernameNotFoundException("");
        }

        UserServiceModel foundedUser = this.userCrudServices.getUserByUsername(usernameOrEmail);

        if (foundedUser != null) {
            return this.modelMapper.map(foundedUser, User.class);
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

        UserServiceModel registeredUser = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        registeredUser.setPassword(this.passwordEncoder.encode(userRegisterBindingModel.getPassword()));
        registeredUser.setRegistrationDate(LocalDateTime.now());
        registeredUser.setAuthorities(this.getInitialAuthorities());
        registeredUser.setAccountNonExpired(true);
        registeredUser.setAccountNonLocked(true);
        registeredUser.setCredentialsNonExpired(true);
        registeredUser.setEnabled(true);

        this.userCrudServices.save(registeredUser);

        return registeredUser;
    }

    private Set<UserRoleServiceModel> getInitialAuthorities() {
        Set<UserRoleServiceModel> resuledRoles = new HashSet<>();

        if (this.userCrudServices.getTotalUsers() == 0) {
            resuledRoles.add(this.userRoleServices.create(UserRoles.OWNER));
            resuledRoles.add(this.userRoleServices.create(UserRoles.ADMIN));
            resuledRoles.add(this.userRoleServices.create(UserRoles.MODERATOR));
            resuledRoles.add(this.userRoleServices.create(UserRoles.USER));
        } else {
            resuledRoles.add(this.userRoleServices.create(UserRoles.USER));
        }
        return resuledRoles;
    }

    @Override
    public void sendPasswordResetEmail(UserForgottenPasswordBindingModel userForgottenPasswordBindingModel) {

        UserServiceModel user = this.modelMapper.map((User) this.loadUserByUsername(userForgottenPasswordBindingModel.getUsernameOrEmail()),UserServiceModel.class);

        PasswordResetTokenServiceModel createdToken = this.passwordResetTokenServices.createToken(user);

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

        PasswordResetTokenServiceModel passwordResetToken = this.passwordResetTokenCrudServices.getTokenByToken(resetToken);
        UserServiceModel user = passwordResetToken.getUser();

        user.setPassword(this.passwordEncoder.encode(userResetPasswordBindingModel.getPassword()));

        this.userCrudServices.save(user);
        this.passwordResetTokenServices.deleteOldestToken(user);
    }

    @Override
    public void follow(UserServiceModel user, UserServiceModel userToFollow) {

        if (user == null || userToFollow == null) {
            throw new UserNotFoundException();
        }

        if(user.getId().equals(userToFollow.getId())){
            throw new UserCannotFollowException();
        }

        boolean isUserAlreadyFollowingHim = this.userCrudServices.isUserAlreadyFollowedUser(user, userToFollow);

        if(isUserAlreadyFollowingHim){
            throw new UserAlreadyFollowingHimException();
        }

        user.getFollowings().add(userToFollow);

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

        user.getFollowings().removeIf(x->x.getId().equals(userToUnfollow.getId()));

        this.userCrudServices.save(user);

    }

    @Override
    public void promote(UserServiceModel user) {
        UserRoleServiceModel currentRole = this.userRoleServices.getRole(user);
        UserRoleServiceModel nextRole = this.userRoleServices.getNextRole(currentRole);

        if(nextRole == null){
            throw new UserRoleNotFoundException();
        }

        if(nextRole.getAuthority().toUpperCase().equals(UserRoles.OWNER.name().toUpperCase())){
            throw new UserRoleNotFoundException();
        }

        user.getAuthorities().add(nextRole);



        this.userCrudServices.save(user);

    }

    @Override
    public void demote(UserServiceModel user) {
        UserRoleServiceModel currentRole = this.userRoleServices.getRole(user);
        UserRoleServiceModel previousRole = this.userRoleServices.getNextRole(currentRole);

        if(previousRole == null){
            throw new UserRoleNotFoundException();
        }

        user.getAuthorities().removeIf(x->x.getAuthority().toUpperCase().equals(currentRole.getAuthority().toUpperCase()));

        this.userCrudServices.save(user);

    }
}
