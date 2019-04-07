package com.ichop.core.areas.user.services;

import com.ichop.core.areas.role.domain.entities.UserRoles;
import com.ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import com.ichop.core.areas.role.exceptions.RoleNotFoundException;
import com.ichop.core.areas.role.services.UserRoleServices;
import com.ichop.core.areas.token.domain.models.binding.PasswordResetTokenCreateBindingModel;
import com.ichop.core.areas.token.domain.models.service.PasswordResetTokenServiceModel;
import com.ichop.core.areas.token.exceptions.TokenNotValidException;
import com.ichop.core.areas.token.services.PasswordResetTokenServices;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.binding.UserForgottenPasswordBindingModel;
import com.ichop.core.areas.user.domain.models.binding.UserRegisterBindingModel;
import com.ichop.core.areas.user.domain.models.binding.UserResetPasswordBindingModel;
import com.ichop.core.areas.user.domain.models.jms.UserUpdateAvatarJmsSendModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.events.UserRoleChangeEvent;
import com.ichop.core.areas.user.exceptions.*;
import com.ichop.core.areas.user.repositories.UserRepository;
import com.ichop.core.base.BaseService;
import com.ichop.core.components.email.EmailServices;
import com.ichop.core.components.jms.JmsServices;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServicesImp extends BaseService<User, UserRepository> implements UserServices {

    private static final String UPDATE_AVATAR_DESTINATION = "ichop_web_storage-set_user_avatar";
    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+\\=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRoleServices userRoleServices;
    private final PasswordResetTokenServices passwordResetTokenServices;
    private final EmailServices emailServices;
    private final JmsServices jmsServices;

    public UserServicesImp(ApplicationEventPublisher applicationEventPublisher, ModelMapper modelMapper, UserRepository repository, BCryptPasswordEncoder passwordEncoder, UserRoleServices userRoleServices, PasswordResetTokenServices passwordResetTokenServices, EmailServices emailServices, JmsServices jmsServices) {
        super(applicationEventPublisher, modelMapper, repository);
        this.passwordEncoder = passwordEncoder;
        this.userRoleServices = userRoleServices;
        this.passwordResetTokenServices = passwordResetTokenServices;
        this.emailServices = emailServices;
        this.jmsServices = jmsServices;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        if (this.isEmail(usernameOrEmail)) {

            UserServiceModel foundedUser = this.findUserByEmail(usernameOrEmail);

            if (foundedUser != null) {
                return this.modelMapper.map(foundedUser, User.class);
            }

            throw new UsernameNotFoundException("");
        }

        UserServiceModel foundedUser = this.findUserByUsername(usernameOrEmail);

        if (foundedUser != null) {
            return this.modelMapper.map(foundedUser, User.class);
        }

        throw new UsernameNotFoundException("");
    }

    @Override
    public UserServiceModel register(UserRegisterBindingModel userRegisterBindingModel) {

        if (this.isUserExistsByUsername(userRegisterBindingModel.getUsername())) {
            throw new UserAlreadyExistsException();
        }

        if (this.isUserExistsByEmail(userRegisterBindingModel.getEmail())) {
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

        UserServiceModel savedUser = this.save(registeredUser, UserServiceModel.class);
        return savedUser;
    }

    @Override
    public void sendUpdateAvatarRequest(String username, String imageAsBase64String) {
        UserUpdateAvatarJmsSendModel bindingModel = new UserUpdateAvatarJmsSendModel();
        bindingModel.setUsername(username);
        bindingModel.setAvatar(imageAsBase64String);

        this.jmsServices.sendModel(bindingModel,UPDATE_AVATAR_DESTINATION);

    }

    @Override
    public Set<UserRoleServiceModel> getInitialAuthorities() {
        Set<UserRoleServiceModel> resuledRoles = new HashSet<>();

        if (this.findTotalUsers() == 0) {
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

        UserDetails userDetails = this.loadUserByUsername(userForgottenPasswordBindingModel.getUsernameOrEmail());
        UserServiceModel user = this.modelMapper.map(userDetails, UserServiceModel.class);

        PasswordResetTokenCreateBindingModel bindingModel = new PasswordResetTokenCreateBindingModel();
        bindingModel.setUser(user);
        PasswordResetTokenServiceModel createdToken = this.passwordResetTokenServices.create(bindingModel);

        this.emailServices.sendResetPasswordEmail(user.getEmail(), createdToken.getToken(), createdToken.getExpiryDate());

    }

    @Override
    public void resetPassword(UserResetPasswordBindingModel bindingModel, String resetToken) {

        if (!this.passwordResetTokenServices.isValid(resetToken)) {
            throw new TokenNotValidException();
        }

        if (!bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            throw new UserPasswordNotValidException();
        }

        PasswordResetTokenServiceModel passwordResetToken = this.passwordResetTokenServices.findByToken(resetToken);
        UserServiceModel user = passwordResetToken.getUser();

        user.setPassword(this.passwordEncoder.encode(bindingModel.getPassword()));

        this.save(user, UserServiceModel.class);
        this.passwordResetTokenServices.deleteOldestByUser(user);

    }

    @Override
    public void resetPassword(UserResetPasswordBindingModel userResetPasswordBindingModel, UserServiceModel user) {

        if (!userResetPasswordBindingModel.getPassword().equals(userResetPasswordBindingModel.getConfirmPassword())) {
            throw new UserPasswordNotValidException();
        }

        user.setPassword(this.passwordEncoder.encode(userResetPasswordBindingModel.getPassword()));

        this.save(user, UserServiceModel.class);
    }

    @Override
    public void follow(UserServiceModel user, UserServiceModel userToFollow) {

        if (user == null || userToFollow == null) {
            throw new UserNotFoundException();
        }

        if (user.getId().equals(userToFollow.getId())) {
            throw new UserCannotFollowException();
        }

        boolean isUserAlreadyFollowingHim = this.isUserAlreadyFollowedUser(user, userToFollow);

        if (isUserAlreadyFollowingHim) {
            throw new UserAlreadyFollowingHimException();
        }

        user.getFollowings().add(userToFollow);

        this.save(user, UserServiceModel.class);
    }

    @Override
    public void unfollow(UserServiceModel user, UserServiceModel userToUnfollow) {

        if (user == null || userToUnfollow == null) {
            throw new UserNotFoundException();
        }

        boolean isEvenUserFollowingHim = this.isUserAlreadyFollowedUser(user, userToUnfollow);

        if (!isEvenUserFollowingHim) {
            throw new UserNotFollowingHimException();
        }

        user.getFollowings().removeIf(x -> x.getId().equals(userToUnfollow.getId()));

        this.save(user, UserServiceModel.class);

    }

    @Override
    public UserServiceModel promote(UserServiceModel user) {

        if (!this.isUserExistsByUsername(user.getUsername())) {
            throw new UserNotFoundException();
        }

        UserRoleServiceModel currentRole = this.userRoleServices.findHighestOfUser(user);
        UserRoleServiceModel nextRole = this.userRoleServices.getUserNextRole(currentRole);

        if (nextRole == null) {
            throw new RoleNotFoundException();
        }

        if (nextRole.getAuthority().toUpperCase().equals(UserRoles.OWNER.name().toUpperCase())) {
            throw new RoleNotFoundException();
        }

        user.getAuthorities().add(nextRole);
        this.createEvent(UserRoleChangeEvent.class, this, this.modelMapper.map(user, User.class));
        return this.save(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel demote(UserServiceModel user) {

        if (!this.isUserExistsByUsername(user.getUsername())) {
            throw new UserNotFoundException();
        }

        UserRoleServiceModel currentRole = this.userRoleServices.findHighestOfUser(user);
        UserRoleServiceModel previousRole = this.userRoleServices.getUserPreviousRole(currentRole);

        if (previousRole == null) {
            throw new RoleNotFoundException();
        }

        user.getAuthorities().removeIf(x -> x.getAuthority().toUpperCase().equals(currentRole.getAuthority().toUpperCase()));

        this.createEvent(UserRoleChangeEvent.class, this, this.modelMapper.map(user, User.class));
        return this.save(user, UserServiceModel.class);
    }




    @Override
    public UserServiceModel findUserByUsername(String username) {

        if (this.isUserExistsByUsername(username)) {
            User entityUser = this.repository.findUserByUsername(username);
            return this.modelMapper.map(entityUser, UserServiceModel.class);
        }

        return null;
    }

    @Override
    public UserServiceModel findUserByEmail(String email) {
        if (this.isUserExistsByEmail(email)) {
            User entityUser = this.repository.findUserByEmail(email);
            return this.modelMapper.map(entityUser, UserServiceModel.class);
        }

        return null;
    }

    @Override
    public boolean isEmail(String value) {
        Matcher matcher = Pattern.compile(EMAIL_PATTERN)
                .matcher(value);
        return matcher.find();
    }

    @Override
    public boolean isUserExistsByUsername(String username) {
        return this.repository.findUserByUsername(username) != null;
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        return this.repository.findUserByEmail(email) != null;
    }

    @Override
    public long findTotalUsers() {
        return this.repository.findAll().size();
    }

    @Override
    public UserServiceModel findUserById(String id) {
        return this.findById(id, UserServiceModel.class);
    }

    @Override
    public void updateLastOnline(UserServiceModel user, LocalDateTime dateTime) {
        User entityUser = this.modelMapper.map(user, User.class);
        this.repository.updateLastOnline(entityUser, dateTime);
    }

    @Override
    public void updateUserLocation(UserServiceModel user, String userLocation) {
        User entityUser = this.modelMapper.map(user, User.class);
        this.repository.updateUserLocation(entityUser, userLocation);
    }

    @Override
    public boolean isUserAlreadyFollowedUser(UserServiceModel user, UserServiceModel followingUser) {

        if (!this.isUserExistsByUsername(user.getUsername()) || !this.isUserExistsByUsername(followingUser.getUsername())) {
            throw new UserNotFoundException();
        }

        return this.repository.isUserAlreadyFollowedUser(user.getId(), followingUser.getId());
    }

    @Override
    public int findUserTotalFollowings(UserServiceModel user) {
        return this.repository.getUserTotalFollowings(user.getId());
    }

    @Override
    public int findUserTotalFollowers(UserServiceModel user) {
        return this.repository.getUserTotalFollowers(user.getId());
    }

    @Override
    public List<UserServiceModel> getFollowers(UserServiceModel user) {

        List<UserServiceModel> result = new LinkedList<>();

        this.repository.findAll().stream().forEach(x -> {

            User foundedUser = x.getFollowings().stream().filter(z -> z.getId().equals(user.getId())).findFirst().orElse(null);

            if (foundedUser != null) {
                result.add(this.modelMapper.map(x, UserServiceModel.class));
            }

        });

        return result;
    }

    @Override
    public Page<UserServiceModel> findUsersByUsernameContains(String containingWord, Pageable pageable) {
        return this.repository
                .findUsersByUsernameContains(containingWord, pageable)
                .map(x -> this.modelMapper.map(x, UserServiceModel.class));
    }

    @Override
    public Page<UserServiceModel> findUsersWhomHasRole(String role, Pageable pageable) {
        return this.repository
                .findUsersWhomHasRole(role, pageable)
                .map(x -> this.modelMapper.map(x, UserServiceModel.class));
    }

    @Override
    public Page<UserServiceModel> findAll(Pageable pageable) {
        return this.findAll(UserServiceModel.class, pageable);
    }
}
