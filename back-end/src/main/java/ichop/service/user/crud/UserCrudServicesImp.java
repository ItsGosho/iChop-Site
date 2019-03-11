package ichop.service.user.crud;

import ichop.domain.entities.users.User;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
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
        User entitiyUser = this.userRepository.findUserByUsername(username);

        return entitiyUser != null;
    }

    @Override
    public boolean existsByEmail(String email) {
        User entitiyUser = this.userRepository.findUserByEmail(email);

        return entitiyUser != null;
    }

    @Override
    public long getTotalUsers() {
        long totalUsers = this.userRepository.findAll().size();

        return totalUsers;
    }

    @Override
    public UserServiceModel getUserById(String id) {
        User entitiyUser = this.userRepository.findById(id).orElse(null);

        if(entitiyUser != null){
            return this.modelMapper.map(entitiyUser,UserServiceModel.class);
        }

        return null;
    }

    @Override
    public UserServiceModel getUserByUsername(String username) {
        User entitiyUser = this.userRepository.findUserByUsername(username);

        if(entitiyUser != null){
            return this.modelMapper.map(entitiyUser,UserServiceModel.class);
        }

        return null;
    }

    @Override
    public UserServiceModel getUserByEmail(String email) {
        User entitiyUser = this.userRepository.findUserByEmail(email);

        if(entitiyUser != null){
            return this.modelMapper.map(entitiyUser,UserServiceModel.class);
        }

        return null;
    }

    @Override
    public void save(UserServiceModel user) {
        User entityUser = this.modelMapper.map(user,User.class);

        this.userRepository.save(entityUser);
    }

    @Override
    public void updateLastOnline(UserServiceModel user, LocalDateTime dateTime) {
        User entityUser = this.modelMapper.map(user,User.class);
        this.userRepository.updateLastOnline(entityUser,dateTime);
    }

    @Override
    public void updateUserLocation(UserServiceModel user, String userLocation) {
        User entityUser = this.modelMapper.map(user,User.class);
        this.userRepository.updateUserLocation(entityUser,userLocation);
    }

    @Override
    public boolean isUserAlreadyFollowedUser(UserServiceModel user, UserServiceModel followingUser) {
        return this.userRepository.isUserAlreadyFollowedUser(user.getId(),followingUser.getId());
    }

    @Override
    public int getUserTotalFollowings(UserServiceModel user) {
        return this.userRepository.getUserTotalFollowings(user.getId());
    }

    @Override
    public int getUserTotalFollowers(UserServiceModel user) {
        return this.userRepository.getUserTotalFollowers(user.getId());
    }

    @Override
    public List<UserServiceModel> getFollowers(UserServiceModel user) {

        List<UserServiceModel> result = new LinkedList<>();

        this.userRepository.findAll().stream().forEach(x->{

            User foundedUser = x.getFollowings().stream().filter(z-> z.getId().equals(user.getId())).findFirst().orElse(null);

            if(foundedUser != null){
                result.add(this.modelMapper.map(x,UserServiceModel.class));
            }

        });

        return result;
    }
}
