package ichop.users.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.constants.UserValidationConstants;
import ichop.users.domain.entities.User;
import ichop.users.domain.enums.Roles;
import ichop.users.domain.models.jms.register.UserRegisterRequest;
import ichop.users.domain.models.service.RoleServiceModel;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ichop.commons.service.AbstractMySQLBaseService;
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

import static ichop.users.constants.UserLogConstants.REGISTRATION_SUCCESSFUL;

@Service
public class UserServicesImp extends AbstractMySQLBaseService<User, UserServiceModel, UserRepository> implements UserServices {

    private static final Logger LOG = LogManager.getLogger(UserServicesImp.class);


    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleServices roleServices;

    @Autowired
    public UserServicesImp(ObjectMapper objectMapper,
                           UserRepository repository,
                           BCryptPasswordEncoder passwordEncoder,
                           RoleServices roleServices) {
        super(objectMapper, repository);
        this.passwordEncoder = passwordEncoder;
        this.roleServices = roleServices;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserServiceModel foundedUser = this.findByEmail(email);

        if (foundedUser != null) {
            return super.objectMapper.convertValue(foundedUser, User.class);
        }

        throw new UsernameNotFoundException("");
    }

    @Override
    public UserServiceModel register(UserRegisterRequest userRegisterRequest) {

        UserServiceModel registeredUser = super.objectMapper.convertValue(userRegisterRequest, UserServiceModel.class);
        registeredUser.setPassword(this.passwordEncoder.encode(userRegisterRequest.getPassword()));
        registeredUser.setRegistrationDate(LocalDateTime.now());
        registeredUser.setAuthorities(this.getInitialAuthorities());
        registeredUser.setAccountNonExpired(true);
        registeredUser.setAccountNonLocked(true);
        registeredUser.setCredentialsNonExpired(true);
        registeredUser.setEnabled(true);

        registeredUser = this.save(registeredUser, UserServiceModel.class);

        LOG.info(String.format(REGISTRATION_SUCCESSFUL, registeredUser.getEmail()));
        return registeredUser;
    }

    @Override
    public Set<RoleServiceModel> getInitialAuthorities() {
        Set<RoleServiceModel> roles = new HashSet<>();

        if (this.findTotalUsers() == 0) {
            roles.add(this.roleServices.create(Roles.OWNER));
            roles.add(this.roleServices.create(Roles.ADMIN));
            roles.add(this.roleServices.create(Roles.MODERATOR));
            roles.add(this.roleServices.create(Roles.USER));
        } else {
            roles.add(this.roleServices.create(Roles.USER));
        }
        return roles;
    }


    @Override
    public UserServiceModel findByUsername(String username) {

        if (this.existsByUsername(username)) {
            User entityUser = this.repository.findUserByUsername(username);
            return super.objectMapper.convertValue(entityUser, UserServiceModel.class);
        }

        return null;
    }

    @Override
    public UserServiceModel findByEmail(String email) {
        if (this.existsByEmail(email)) {
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
    public boolean existsByUsername(String username) {
        return this.repository.findUserByUsername(username) != null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.repository.findUserByEmail(email) != null;
    }

    @Override
    public long findTotalUsers() {
        return this.repository.findAll().size();
    }

    @Override
    public void updateLastOnline(UserServiceModel user, LocalDateTime dateTime) {
        User entityUser = super.objectMapper.convertValue(user, User.class);
        //this.repository.updateLastOnline(entityUser, dateTime);
    }

    @Override
    public void updateLocation(UserServiceModel user, String userLocation) {
        User entityUser = super.objectMapper.convertValue(user, User.class);
        //this.repository.updateLocation(entityUser, userLocation);
    }

    @Override
    public void changePassword(String username, String password) {
        String encodedPassword = this.passwordEncoder.encode(password);

        UserServiceModel user = this.findByUsername(username);
        user.setPassword(encodedPassword);

        this.save(user);
    }

    @Override
    public boolean hasNextRole(String username) {
        UserServiceModel user = this.findByUsername(username);
        RoleServiceModel currentRole = this.roleServices.findHighestOfUser(user);
        RoleServiceModel nextRole = this.roleServices.getUserNextRole(currentRole);

        return nextRole != null && !nextRole.getAuthority().toUpperCase().equals(Roles.OWNER.name().toUpperCase());
    }

    @Override
    public boolean hasPreviousRole(String username) {
        UserServiceModel user = this.findByUsername(username);
        RoleServiceModel currentRole = this.roleServices.findHighestOfUser(user);
        RoleServiceModel previousRole = this.roleServices.getUserPreviousRole(currentRole);

        return previousRole != null;
    }

    @Override
    public UserServiceModel promote(String username) {
        UserServiceModel user = this.findByUsername(username);

        RoleServiceModel currentRole = this.roleServices.findHighestOfUser(user);
        RoleServiceModel nextRole = this.roleServices.getUserNextRole(currentRole);

        user.getAuthorities().add(nextRole);
        return this.save(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel demote(String username) {
        UserServiceModel user = this.findByUsername(username);

        RoleServiceModel currentRole = this.roleServices.findHighestOfUser(user);
        RoleServiceModel previousRole = this.roleServices.getUserPreviousRole(currentRole);

        user.getAuthorities().removeIf(x -> x.getAuthority().toUpperCase().equals(currentRole.getAuthority().toUpperCase()));

        return this.save(user, UserServiceModel.class);
    }
}
