package ichop.service.user.crud;

import ichop.domain.entities.users.User;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserCrudServicesImp implements UserCrudServices {

    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+\\=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserCrudServicesImp(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isEmail(String value) {
        Matcher matcher = Pattern.compile(EMAIL_PATTERN)
                .matcher(value);
        return matcher.find();
    }

    @Override
    public boolean existsByUsername(String username) {
        User user = this.userRepository.findUserByUsername(username);

        return user != null;
    }

    @Override
    public boolean existsByEmail(String email) {
        User user = this.userRepository.findUserByEmail(email);

        return user != null;
    }

    @Override
    public long getTotalUsers() {
        long totalUsers = this.userRepository.findAll().size();

        return totalUsers;
    }

    @Override
    public UserServiceModel getUserById(String id) {
        User user = this.userRepository.findById(id).orElse(null);

        if(user != null){
            return this.modelMapper.map(user,UserServiceModel.class);
        }

        return null;
    }

    @Override
    public UserServiceModel getUserByUsername(String username) {
        User user = this.userRepository.findUserByUsername(username);

        if(user != null){
            return this.modelMapper.map(user,UserServiceModel.class);
        }

        return null;
    }

    @Override
    public UserServiceModel getUserByEmail(String email) {
        User user = this.userRepository.findUserByEmail(email);

        if(user != null){
            return this.modelMapper.map(user,UserServiceModel.class);
        }

        return null;
    }

    @Override
    public void save(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel,User.class);

        this.userRepository.save(user);
    }

    @Override
    public void updateLastOnline(UserServiceModel userServiceModel, LocalDateTime dateTime) {
        User user = this.modelMapper.map(userServiceModel,User.class);
        this.userRepository.updateLastOnline(user,dateTime);
    }

    @Override
    public void updateUserLocation(UserServiceModel userServiceModel, String userLocation) {
        User user = this.modelMapper.map(userServiceModel,User.class);
        this.userRepository.updateUserLocation(user,userLocation);
    }

    @Override
    public boolean isUserAlreadyFollowedUser(UserServiceModel user, UserServiceModel followingUser) {
        return this.userRepository.isUserAlreadyFollowedUser(user.getId(),followingUser.getId());
    }
}
