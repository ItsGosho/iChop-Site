package com.ichop.core.areas.user.helpers.view.user_follow_all;

import com.ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import com.ichop.core.areas.role.services.UserRoleServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_follow_all.UserFollowAllViewModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import com.ichop.core.areas.user.services.UserFollowServices;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.base.BaseViewCreator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class UserFollowAllViewHelper extends BaseViewCreator {

    private final UserServices userServices;
    private final UserFollowServices userFollowServices;
    private final UserRoleServices userRoleServices;

    @Autowired
    public UserFollowAllViewHelper(ModelMapper modelMapper, UserServices userServices, UserFollowServices userFollowServices, UserRoleServices userRoleServices) {
        super(modelMapper);
        this.userServices = userServices;
        this.userFollowServices = userFollowServices;
        this.userRoleServices = userRoleServices;
    }

    public List<UserFollowAllViewModel> createFollowers(String username) {
        UserServiceModel user = this.userServices.findUserByUsername(username);

        if (user == null) {
            throw new UserNotFoundException();
        }

        List<UserServiceModel> followers = this.userFollowServices.getFollowers(user);

        return this.create(followers);
    }

    public List<UserFollowAllViewModel> createFollowings(String username) {
        UserServiceModel user = this.userServices.findUserByUsername(username);

        if (user == null) {
            throw new UserNotFoundException();
        }

        List<UserServiceModel> followings = this.userFollowServices.getFollowings(user);

        return this.create(followings);
    }

    private List<UserFollowAllViewModel> create(List<UserServiceModel> data){
        List<UserFollowAllViewModel> result = new LinkedList<>();

        data.forEach(x -> {
            UserRoleServiceModel userRole = this.userRoleServices.findHighestOfUser(x);

            UserFollowAllViewModel userFollowAllViewModel = this.modelMapper.map(x, UserFollowAllViewModel.class);
            userFollowAllViewModel.setRole(userRole.getAuthority());
            result.add(userFollowAllViewModel);
        });

        return result;
    }

}
