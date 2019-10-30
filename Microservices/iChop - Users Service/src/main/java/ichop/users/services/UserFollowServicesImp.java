package ichop.users.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.common.service.AbstractBaseService;
import ichop.users.domain.entities.User;
import ichop.users.domain.entities.UserFollow;
import ichop.users.domain.models.service.UserFollowServiceModel;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.repositories.UserFollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserFollowServicesImp
        extends AbstractBaseService<UserFollow, UserFollowServiceModel, UserFollowRepository>
        implements UserFollowServices {

    @Autowired
    public UserFollowServicesImp(ObjectMapper objectMapper, UserFollowRepository repository) {
        super(objectMapper, repository);
    }

    @Override
    public void follow(UserServiceModel user, UserServiceModel userToFollow) {
        UserFollowServiceModel userFollow = super.objectMapper.convertValue(user,UserFollowServiceModel.class);
        userFollow.setUser(userToFollow);
        userFollow.setFollower(user);
        userFollow.setSince(LocalDateTime.now());

        this.save(userFollow, UserFollowServiceModel.class);
    }

    @Override
    public void unfollow(UserServiceModel user, UserServiceModel userToUnfollow) {
        User entityUser = super.objectMapper.convertValue(user,User.class);
        User entityUserToUnfollow = super.objectMapper.convertValue(userToUnfollow,User.class);

        UserFollow userFollow = super.repository.findByUserAndFollower(entityUserToUnfollow,entityUser);
        UserFollowServiceModel userFollowServiceModel = super.objectMapper.convertValue(userFollow,UserFollowServiceModel.class);

        this.delete(userFollowServiceModel);

    }


    @Override
    public boolean isFollowed(UserServiceModel user, UserServiceModel follow) {

        User entityUser = super.objectMapper.convertValue(user,User.class);
        User entityFollowedUser = super.objectMapper.convertValue(follow,User.class);
        return this.repository.isUserAlreadyFollowedUser(entityUser, entityFollowedUser);
    }

    @Override
    public Long findTotalFollowings(UserServiceModel user) {
        User entityUser = super.objectMapper.convertValue(user,User.class);
        return this.repository.countByFollower(entityUser);
    }

    @Override
    public Long findTotalFollowers(UserServiceModel user) {
        User entityUser = super.objectMapper.convertValue(user,User.class);
        return this.repository.countByUser(entityUser);
    }

    @Override
    public List<UserServiceModel> getFollowers(UserServiceModel user) {

        List<UserServiceModel> result = new LinkedList<>();

        User entityUser = super.objectMapper.convertValue(user,User.class);
        List<UserFollow> followers = this.repository.findAllByUser(entityUser);

        followers.forEach(x -> {
           result.add(super.objectMapper.convertValue(x.getFollower(), UserServiceModel.class));
        });

        return result;
    }

    @Override
    public List<UserServiceModel> getFollowings(UserServiceModel user) {

        List<UserServiceModel> result = new LinkedList<>();

        User entityUser = super.objectMapper.convertValue(user,User.class);
        List<UserFollow> followings = this.repository.findAllByFollower(entityUser);

        followings.forEach(x -> {
            result.add(super.objectMapper.convertValue(x.getUser(), UserServiceModel.class));
        });

        return result;
    }
}
