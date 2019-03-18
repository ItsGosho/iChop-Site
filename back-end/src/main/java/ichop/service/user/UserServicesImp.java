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
import ichop.events.UserRoleChangeEvent;
import ichop.exceptions.role.UserRoleNotFoundException;
import ichop.exceptions.token.TokenNotValidException;
import ichop.exceptions.user.*;
import ichop.repository.user.UserRepository;
import ichop.service.BaseService;
import ichop.service.role.UserRoleServices;
import ichop.service.token.PasswordResetTokenServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServicesImp extends BaseService<User, UserRepository> implements UserServices {

    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+\\=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRoleServices userRoleServices;
    private final PasswordResetTokenServices passwordResetTokenServices;
    private final EmailServices emailServices;

    @Autowired
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

        UserServiceModel savedUser = super.save(registeredUser, UserServiceModel.class);
        return savedUser;
    }

    private Set<UserRoleServiceModel> getInitialAuthorities() {
        Set<UserRoleServiceModel> resuledRoles = new HashSet<>();

        if (this.findTotalUsers() == 0) {
            resuledRoles.add(this.userRoleServices.createRole(UserRoles.OWNER));
            resuledRoles.add(this.userRoleServices.createRole(UserRoles.ADMIN));
            resuledRoles.add(this.userRoleServices.createRole(UserRoles.MODERATOR));
            resuledRoles.add(this.userRoleServices.createRole(UserRoles.USER));
        } else {
            resuledRoles.add(this.userRoleServices.createRole(UserRoles.USER));
        }
        return resuledRoles;
    }

    @Override
    public void sendPasswordResetEmail(UserForgottenPasswordBindingModel userForgottenPasswordBindingModel) {

        UserServiceModel user = this.modelMapper.map((User) this.loadUserByUsername(userForgottenPasswordBindingModel.getUsernameOrEmail()), UserServiceModel.class);

        PasswordResetTokenServiceModel createdToken = this.passwordResetTokenServices.createToken(user);

        this.emailServices.sendResetPasswordEmail(user.getEmail(), createdToken.getToken(), createdToken.getExpiryDate());

    }

    @Override
    public void resetPassword(UserResetPasswordBindingModel userResetPasswordBindingModel, String resetToken) {

        if (!this.passwordResetTokenServices.isTokenValid(resetToken)) {
            throw new TokenNotValidException();
        }

        if (!userResetPasswordBindingModel.getPassword().equals(userResetPasswordBindingModel.getConfirmPassword())) {
            throw new UserPasswordNotValidException();
        }

        PasswordResetTokenServiceModel passwordResetToken = this.passwordResetTokenServices.findTokenByToken(resetToken);
        UserServiceModel user = passwordResetToken.getUser();

        user.setPassword(this.passwordEncoder.encode(userResetPasswordBindingModel.getPassword()));

        super.save(user, UserServiceModel.class);
        this.passwordResetTokenServices.deleteOldestTokenByUser(user);

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

        super.save(user, UserServiceModel.class);
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

        super.save(user, UserServiceModel.class);

    }

    @Override
    public UserServiceModel promote(UserServiceModel user) {
        UserRoleServiceModel currentRole = this.userRoleServices.findHighestRoleOfUser(user);
        UserRoleServiceModel nextRole = this.userRoleServices.getUserNextRole(currentRole);

        if (nextRole == null) {
            throw new UserRoleNotFoundException();
        }

        if (nextRole.getAuthority().toUpperCase().equals(UserRoles.OWNER.name().toUpperCase())) {
            throw new UserRoleNotFoundException();
        }

        user.getAuthorities().add(nextRole);
        super.createEvent(UserRoleChangeEvent.class,this,super.modelMapper.map(user,User.class));
        return super.save(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel demote(UserServiceModel user) {

        UserRoleServiceModel currentRole = this.userRoleServices.findHighestRoleOfUser(user);
        UserRoleServiceModel previousRole = this.userRoleServices.getUserNextRole(currentRole);

        if (previousRole == null) {
            throw new UserRoleNotFoundException();
        }

        user.getAuthorities().removeIf(x -> x.getAuthority().toUpperCase().equals(currentRole.getAuthority().toUpperCase()));

        super.createEvent(UserRoleChangeEvent.class,this,super.modelMapper.map(user,User.class));
        return super.save(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findUserByUsername(String username) {

        if(this.isUserExistsByUsername(username)){
            User entityUser = super.repository.findUserByUsername(username);
            return super.modelMapper.map(entityUser, UserServiceModel.class);
        }

        return null;
    }

    @Override
    public UserServiceModel findUserByEmail(String email) {
        if(this.isUserExistsByUsername(email)){
            User entityUser = super.repository.findUserByEmail(email);
            return super.modelMapper.map(entityUser, UserServiceModel.class);
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
        return super.repository.findUserByUsername(username) != null;
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        return super.repository.findUserByEmail(email) != null;
    }

    @Override
    public long findTotalUsers() {
        return super.repository.findAll().size();
    }

    @Override
    public UserServiceModel findUserById(String id) {
        return super.findById(id, UserServiceModel.class);
    }

    @Override
    public void updateLastOnline(UserServiceModel user, LocalDateTime dateTime) {
        User entityUser = this.modelMapper.map(user, User.class);
        super.repository.updateLastOnline(entityUser, dateTime);
    }

    @Override
    public void updateUserLocation(UserServiceModel user, String userLocation) {
        User entityUser = this.modelMapper.map(user, User.class);
        super.repository.updateUserLocation(entityUser, userLocation);
    }

    @Override
    public boolean isUserAlreadyFollowedUser(UserServiceModel user, UserServiceModel followingUser) {
        return super.repository.isUserAlreadyFollowedUser(user.getId(), followingUser.getId());
    }

    @Override
    public int findUserTotalFollowings(UserServiceModel user) {
        return super.repository.getUserTotalFollowings(user.getId());
    }

    @Override
    public int findUserTotalFollowers(UserServiceModel user) {
        return super.repository.getUserTotalFollowers(user.getId());
    }

    @Override
    public List<UserServiceModel> getFollowers(UserServiceModel user) {

        List<UserServiceModel> result = new LinkedList<>();

        super.repository.findAll().stream().forEach(x -> {

            User foundedUser = x.getFollowings().stream().filter(z -> z.getId().equals(user.getId())).findFirst().orElse(null);

            if (foundedUser != null) {
                result.add(this.modelMapper.map(x, UserServiceModel.class));
            }

        });

        return result;
    }

    @Override
    public Page<UserServiceModel> findUsersByUsernameContains(String containingWord, Pageable pageable) {
        return super.repository
                .findUsersByUsernameContains(containingWord, pageable)
                .map(x -> this.modelMapper.map(x, UserServiceModel.class));
    }

    @Override
    public Page<UserServiceModel> findUsersWhomHasRole(String role, Pageable pageable) {
        return super.repository
                .findUsersWhomHasRole(role, pageable)
                .map(x -> this.modelMapper.map(x, UserServiceModel.class));
    }

    @Override
    public Page<UserServiceModel> findAll(Pageable pageable) {
        return super.findAll(UserServiceModel.class,pageable);
    }
}
