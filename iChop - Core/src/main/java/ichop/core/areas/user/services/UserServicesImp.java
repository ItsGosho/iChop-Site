package ichop.core.areas.user.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.core.areas.role.domain.enums.UserRoles;
import ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import ichop.core.areas.role.services.UserRoleServices;
import ichop.core.areas.user.constants.UserValidationConstants;
import ichop.core.areas.user.domain.entities.User;
import ichop.core.areas.user.domain.models.binding.UserRegisterBindingModel;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.areas.user.repositories.UserRepository;
import ichop.core.common.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServicesImp extends AbstractBaseService<User, UserServiceModel, UserRepository> implements UserServices {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRoleServices userRoleServices;

    @Autowired
    public UserServicesImp(ObjectMapper objectMapper,
                           UserRepository repository,
                           BCryptPasswordEncoder passwordEncoder,
                           UserRoleServices userRoleServices) {
        super(objectMapper, repository);
        this.passwordEncoder = passwordEncoder;
        this.userRoleServices = userRoleServices;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        if (this.isEmail(usernameOrEmail)) {

            UserServiceModel foundedUser = this.findUserByEmail(usernameOrEmail);

            if (foundedUser != null) {
                return super.objectMapper.convertValue(foundedUser, User.class);
            }
        }

        throw new UsernameNotFoundException("Proba");
    }

    @Override
    public UserServiceModel register(UserRegisterBindingModel userRegisterBindingModel) {

        UserServiceModel registeredUser = super.objectMapper.convertValue(userRegisterBindingModel, UserServiceModel.class);
        registeredUser.setPassword(this.passwordEncoder.encode(userRegisterBindingModel.getPassword()));
        registeredUser.setRegistrationDate(LocalDateTime.now());
        registeredUser.setAuthorities(this.getInitialAuthorities());
        registeredUser.setAccountNonExpired(true);
        registeredUser.setAccountNonLocked(true);
        registeredUser.setCredentialsNonExpired(true);
        registeredUser.setEnabled(true);

        return this.save(registeredUser, UserServiceModel.class);
    }

    @Override
    public Set<UserRoleServiceModel> getInitialAuthorities() {
        Set<UserRoleServiceModel> roles = new HashSet<>();

        if (this.findTotalUsers() == 0) {
            roles.add(this.userRoleServices.create(UserRoles.OWNER));
            roles.add(this.userRoleServices.create(UserRoles.ADMIN));
            roles.add(this.userRoleServices.create(UserRoles.MODERATOR));
            roles.add(this.userRoleServices.create(UserRoles.USER));
        } else {
            roles.add(this.userRoleServices.create(UserRoles.USER));
        }
        return roles;
    }


    @Override
    public UserServiceModel findUserByUsername(String username) {

        if (this.isUserExistsByUsername(username)) {
            User entityUser = this.repository.findUserByUsername(username);
            return super.objectMapper.convertValue(entityUser, UserServiceModel.class);
        }

        return null;
    }

    @Override
    public UserServiceModel findUserByEmail(String email) {
        if (this.isUserExistsByEmail(email)) {
            User entityUser = this.repository.findUserByEmail(email);
            return super.objectMapper.convertValue(entityUser, UserServiceModel.class);
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
        User entityUser = super.objectMapper.convertValue(user, User.class);
        //this.repository.updateLastOnline(entityUser, dateTime);
    }

    @Override
    public void updateUserLocation(UserServiceModel user, String userLocation) {
        User entityUser = super.objectMapper.convertValue(user, User.class);
        //this.repository.updateLocation(entityUser, userLocation);
    }
}
