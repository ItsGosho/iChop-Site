package ichop.core.areas.user.services;

import ichop.core.areas.user.domain.entities.User;
import ichop.core.areas.user.domain.entities.UserFollow;
import ichop.core.areas.user.domain.models.service.UserFollowServiceModel;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.areas.user.exceptions.UserAlreadyFollowingHimException;
import ichop.core.areas.user.exceptions.UserCannotFollowException;
import ichop.core.areas.user.exceptions.UserNotFollowingHimException;
import ichop.core.areas.user.exceptions.UserNotFoundException;
import ichop.core.areas.user.repositories.UserFollowRepository;
import ichop.core.base.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserFollowServicesImp extends BaseService<UserFollow, UserFollowRepository> implements UserFollowServices {


    public UserFollowServicesImp(ModelMapper modelMapper, UserFollowRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public void follow(UserServiceModel user, UserServiceModel userToFollow) {

        if (user == null || userToFollow == null) {
            throw new UserNotFoundException();
        }

        if (user.getId().equals(userToFollow.getId())) {
            throw new UserCannotFollowException();
        }

        boolean isUserAlreadyFollowingHim = this.isUserAlreadyFollowedUser(user,userToFollow);

        if (isUserAlreadyFollowingHim) {
            throw new UserAlreadyFollowingHimException();
        }

        UserFollowServiceModel userFollow = this.modelMapper.map(user,UserFollowServiceModel.class);
        userFollow.setUser(userToFollow);
        userFollow.setFollower(user);
        userFollow.setSince(LocalDateTime.now());

        this.save(userFollow, UserFollowServiceModel.class);
    }

    @Override
    public void unfollow(UserServiceModel user, UserServiceModel userToUnfollow) {

        if (user == null || userToUnfollow == null) {
            throw new UserNotFoundException();
        }

        boolean isEvenUserFollowingHim = this.isUserAlreadyFollowedUser(user,userToUnfollow);

        if (!isEvenUserFollowingHim) {
            throw new UserNotFollowingHimException();
        }

        User entityUser = this.modelMapper.map(user,User.class);
        User entityUserToUnfollow = this.modelMapper.map(userToUnfollow,User.class);

        UserFollow userFollow = this.repository.findByUserAndHisFollower(entityUserToUnfollow,entityUser);
        UserFollowServiceModel userFollowServiceModel = this.modelMapper.map(userFollow,UserFollowServiceModel.class);

        this.delete(userFollowServiceModel);

    }


    @Override
    public boolean isUserAlreadyFollowedUser(UserServiceModel user, UserServiceModel followedUser) {

        if (user == null || followedUser == null) {
            throw new UserNotFoundException();
        }

        User entityUser = this.modelMapper.map(user,User.class);
        User entityFollowedUser = this.modelMapper.map(followedUser,User.class);
        return this.repository.isUserAlreadyFollowedUser(entityUser, entityFollowedUser);
    }

    @Override
    public int findUserTotalFollowings(UserServiceModel user) {
        User entityUser = this.modelMapper.map(user,User.class);
        return this.repository.getUserTotalFollowings(entityUser);
    }

    @Override
    public int findUserTotalFollowers(UserServiceModel user) {
        User entityUser = this.modelMapper.map(user,User.class);
        return this.repository.getUserTotalFollowers(entityUser);
    }

    @Override
    public List<UserServiceModel> getFollowers(UserServiceModel user) {

        List<UserServiceModel> result = new LinkedList<>();

        User entityUser = this.modelMapper.map(user,User.class);
        List<UserFollow> followers = this.repository.findUserFollowers(entityUser);

        followers.forEach(x -> {
           result.add(this.modelMapper.map(x.getFollower(), UserServiceModel.class));
        });

        return result;
    }

    @Override
    public List<UserServiceModel> getFollowings(UserServiceModel user) {

        List<UserServiceModel> result = new LinkedList<>();

        User entityUser = this.modelMapper.map(user,User.class);
        List<UserFollow> followings = this.repository.findUserFollowings(entityUser);

        followings.forEach(x -> {
            result.add(this.modelMapper.map(x.getUser(), UserServiceModel.class));
        });

        return result;
    }
}
