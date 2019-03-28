package com.ichop.core.helpers.view.user_profile;

import com.ichop.core.domain.models.service.post.PostServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.domain.models.view.user_profile.PostsUserProfileViewModel;
import com.ichop.core.helpers.view.BaseViewCreator;
import com.ichop.core.service.post.PostServices;
import com.ichop.core.service.user.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostsUserProfileViewHelper extends BaseViewCreator {

    private final UserServices userServices;
    private final PostServices postServices;

    @Autowired
    protected PostsUserProfileViewHelper(ModelMapper modelMapper, UserServices userServices, PostServices postServices) {
        super(modelMapper);
        this.userServices = userServices;
        this.postServices = postServices;
    }


    public List<PostsUserProfileViewModel> create(String username) {
        UserServiceModel user = this.userServices.findUserByUsername(username);
        List<PostServiceModel> posts = this.postServices.findPostsByUser(user);
        List<PostsUserProfileViewModel> result = posts.stream().map(x -> super.modelMapper.map(x, PostsUserProfileViewModel.class)).collect(Collectors.toList());;

        return result.stream().sorted((x1, x2) -> x2.getCreatedOn().compareTo(x1.getCreatedOn())).collect(Collectors.toList());
    }

}
