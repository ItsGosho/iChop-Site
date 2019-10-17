package com.ichop.core.areas.post.services;

import com.ichop.core.areas.post.domain.models.binding.PostCreateBindingModel;
import com.ichop.core.areas.post.domain.models.service.PostServiceModel;
import com.ichop.core.areas.post.exceptions.PostNotFoundException;
import com.ichop.core.areas.post.repositories.PostRepository;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostServicesUnitTests {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServicesImp postServices;


    @Before
    public void setUp() {
    }

    @Test(expected = UserNotFoundException.class)
    public void create_withNullUser_shouldThrowException(){
        PostCreateBindingModel bindingModel = mock(PostCreateBindingModel.class);

        when(bindingModel.getUser()).thenReturn(null);

        this.postServices.create(bindingModel);
    }

    @Test(expected = UserNotFoundException.class)
    public void create_withNullCreator_shouldThrowException(){
        PostCreateBindingModel bindingModel = mock(PostCreateBindingModel.class);

        when(bindingModel.getUser()).thenReturn(mock(UserServiceModel.class));
        when(bindingModel.getCreator()).thenReturn(null);

        this.postServices.create(bindingModel);
    }

    @Test
    public void create_withValidData_shouldInvokeMethods(){
        UserServiceModel user = mock(UserServiceModel.class);
        UserServiceModel creator = mock(UserServiceModel.class);
        PostCreateBindingModel bindingModel = mock(PostCreateBindingModel.class);
        PostServiceModel post = mock(PostServiceModel.class);

        when(bindingModel.getUser()).thenReturn(user);
        when(bindingModel.getCreator()).thenReturn(creator);
        when(this.modelMapper.map(bindingModel,PostServiceModel.class)).thenReturn(post);

        this.postServices.create(bindingModel);

        verify(this.modelMapper,times(1)).map(bindingModel,PostServiceModel.class);
        verify(post,times(1)).setCreatedOn(any());
        verify(this.postRepository,times(1)).save(any());

    }

    @Test(expected = UserNotFoundException.class)
    public void findByUser_withNotExistingUser_shouldThrowException(){
        this.postServices.findByUser(null);
    }

    @Test
    public void findByUser_withValidData_shouldInvokeMethods(){
        UserServiceModel user = mock(UserServiceModel.class);
        User entityUser = mock(User.class);

        when(this.modelMapper.map(user,User.class)).thenReturn(entityUser);

        this.postServices.findByUser(user);

        verify(this.modelMapper,times(1)).map(user,User.class);
        verify(this.postRepository,times(1)).findAllByUser(entityUser);
    }

    @Test
    public void findById_withNoExistingPost_shouldReturnNull(){
        PostServiceModel result = this.postServices.findById("id");

        assertNull(result);
    }

    @Test(expected = PostNotFoundException.class)
    public void delete_withNullPost_shouldThrowException(){
        this.postServices.deleteByModel(null);
    }
    @Test(expected = PostNotFoundException.class)
    public void delete_withNotExistingPost_shouldThrowException(){
        PostServiceModel post = mock(PostServiceModel.class);

        when(post.getId()).thenReturn("id");
        when(this.postServices.existsById("id")).thenReturn(false);

        this.postServices.deleteByModel(post);
    }

    @Test
    public void delete_withValidData_shouldInvokeMethods(){
        PostServiceModel post = mock(PostServiceModel.class);

        when(post.getId()).thenReturn("id");
        when(this.postServices.existsById("id")).thenReturn(true);
        this.postServices.delete(post);

        verify(this.postRepository,times(1)).deleteById("id");
    }

}
