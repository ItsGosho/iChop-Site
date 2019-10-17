package com.ichop.core.areas.user.services;

import com.ichop.core.areas.role.domain.entities.UserRoles;
import com.ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import com.ichop.core.areas.role.exceptions.RoleNotFoundException;
import com.ichop.core.areas.role.services.UserRoleServices;
import com.ichop.core.areas.token.domain.models.binding.PasswordResetTokenCreateBindingModel;
import com.ichop.core.areas.token.domain.models.service.PasswordResetTokenServiceModel;
import com.ichop.core.areas.token.exceptions.TokenNotValidException;
import com.ichop.core.areas.token.services.PasswordResetTokenServices;
import com.ichop.core.areas.user.constants.UserValidationConstants;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.binding.UserForgottenPasswordBindingModel;
import com.ichop.core.areas.user.domain.models.binding.UserRegisterBindingModel;
import com.ichop.core.areas.user.domain.models.binding.UserResetPasswordBindingModelByToken;
import com.ichop.core.areas.user.domain.models.binding.UserResetPasswordBindingModelByUser;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.events.UserRoleChangeEvent;
import com.ichop.core.areas.user.exceptions.UserAlreadyExistsException;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import com.ichop.core.areas.user.exceptions.UserPasswordNotValidException;
import com.ichop.core.areas.user.repositories.UserRepository;
import com.ichop.core.base.BaseService;
import com.ichop.core.areas.email.services.EmailServices;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserServicesImp extends BaseService<User, UserRepository> implements UserServices {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRoleServices userRoleServices;
    private final PasswordResetTokenServices passwordResetTokenServices;
    private final EmailServices emailServices;

    public UserServicesImp(ApplicationEventPublisher applicationEventPublisher, ModelMapper modelMapper, UserRepository repository, BCryptPasswordEncoder passwordEncoder, UserRoleServices userRoleServices, PasswordResetTokenServices passwordResetTokenServices, EmailServices emailServices) {
        super(applicationEventPublisher, modelMapper, repository);
        this.passwordEncoder = passwordEncoder;
        this.userRoleServices = userRoleServices;
        this.passwordResetTokenServices = passwordResetTokenServices;
        this.emailServices = emailServices;
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
    public void resetPassword(UserResetPasswordBindingModelByToken bindingModel) {

        if (!this.passwordResetTokenServices.isValid(bindingModel.getToken())) {
            throw new TokenNotValidException();
        }

        if (!bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            throw new UserPasswordNotValidException();
        }

        PasswordResetTokenServiceModel passwordResetToken = this.passwordResetTokenServices.findByToken(bindingModel.getToken());
        UserServiceModel user = passwordResetToken.getUser();

        user.setPassword(this.passwordEncoder.encode(bindingModel.getPassword()));

        this.save(user, UserServiceModel.class);
        this.passwordResetTokenServices.deleteOldestByUser(user);

    }

    @Override
    public void resetPassword(UserResetPasswordBindingModelByUser bindingModel) {

        if(bindingModel.getUser() == null){
            throw new UserNotFoundException();
        }

        if (!bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            throw new UserPasswordNotValidException();
        }

        bindingModel.getUser().setPassword(this.passwordEncoder.encode(bindingModel.getPassword()));

        this.save(bindingModel.getUser(), UserServiceModel.class);
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
        Matcher matcher = Pattern.compile(UserValidationConstants.EMAIL_PATTERN)
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
        this.repository.updateLocation(entityUser, userLocation);
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
    public List<UserServiceModel> findUsersWhomHasRole(String role) {
        return this.repository
                .findUsersWhomHasRole(role)
                .stream()
                .map(x -> this.modelMapper.map(x, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserServiceModel> findAll(Pageable pageable) {
        return this.findAll(UserServiceModel.class, pageable);
    }
}
