package com.ichop.core.areas.user.view.user_profile;

import com.ichop.core.areas.post.domain.models.service.PostServiceModel;
import com.ichop.core.areas.post.services.PostServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_profile.PostsUserProfileViewModel;
import com.ichop.core.areas.user.helpers.view.user_profile.PostsUserProfileViewHelper;
import com.ichop.core.areas.user.services.UserServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostsUserProfileViewHelperUnitTests {

    @Mock
    private UserServices userServices;

    @Mock
    private PostServices postServices;

    @Mock
    private ModelMapper modelMapper;

    @Spy
    @InjectMocks
    private PostsUserProfileViewHelper postsUserProfileViewHelper;


    @Test
    public void create_withValidData_shouldInvokeMethods() {
        String username = "username";
        UserServiceModel user = mock(UserServiceModel.class);
        List<PostServiceModel> posts = new LinkedList<>();
        List<PostsUserProfileViewModel> result = new LinkedList<>();

        when(this.userServices.findUserByUsername(username)).thenReturn(user);
        when(this.postServices.findByUser(user)).thenReturn(posts);

        this.postsUserProfileViewHelper.create(username);

        verify(this.userServices,times(1)).findUserByUsername(username);
        verify(this.postServices,times(1)).findByUser(user);

    }

}
