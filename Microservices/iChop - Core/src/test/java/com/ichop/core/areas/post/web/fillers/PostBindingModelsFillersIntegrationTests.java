package com.ichop.core.areas.post.web.fillers;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.post.domain.models.binding.PostCreateBindingModel;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.repositories.UserRepository;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.areas.user.services.UserServicesImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Principal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class PostBindingModelsFillersIntegrationTests {

    @Autowired
    private UserRepository userRepository;

    private UserServices userServices;
    private EntityFactory entityFactory;
    private ModelMapper modelMapper;

    private PostBindingModelsFillers postBindingModelsFillers;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.entityFactory = new EntityFactory();

        this.userServices = new UserServicesImp(null, this.modelMapper, this.userRepository, null, null, null, null);
        this.postBindingModelsFillers = new PostBindingModelsFillers(this.userServices);
    }

    @Test
    public void fill_withNotExistingUser_shouldSetUserToNull(){
        String userUsername = "notExistingUsername";
        User creator = this.userRepository.save(this.entityFactory.createUserRANDOM());
        PostCreateBindingModel postCreateBindingModel = new PostCreateBindingModel();
        Principal principal = () -> creator.getUsername();

        PostCreateBindingModel result = this.postBindingModelsFillers.fill(postCreateBindingModel,userUsername,principal);

        assertEquals(creator.getUsername(),result.getCreator().getUsername());
        assertNull(result.getUser());

    }

    @Test
    public void fill_withNotExistingCreator_shouldSetCreatorToNull(){
        String creatorUsername = "notExistingUsername";
        User user = this.userRepository.save(this.entityFactory.createUserRANDOM());
        PostCreateBindingModel postCreateBindingModel = new PostCreateBindingModel();
        Principal principal = () -> creatorUsername;

        PostCreateBindingModel result = this.postBindingModelsFillers.fill(postCreateBindingModel,user.getUsername(),principal);

        assertEquals(user.getUsername(),result.getUser().getUsername());
        assertNull(result.getCreator());

    }

    @Test
    public void fill_withNotExistingCreatorAndUser_shouldSetCreatorAndUserToNull(){
        String creatorUsername = "notExistingUsername";
        String userUsername = "againNotExisting";
        PostCreateBindingModel postCreateBindingModel = new PostCreateBindingModel();
        Principal principal = () -> creatorUsername;

        PostCreateBindingModel result = this.postBindingModelsFillers.fill(postCreateBindingModel,userUsername,principal);

        assertNull(result.getUser());
        assertNull(result.getCreator());

    }

    @Test
    public void fill_withValidData_shouldSetCreatorAndUser(){
        User user = this.userRepository.save(this.entityFactory.createUserRANDOM());
        User creator = this.userRepository.save(this.entityFactory.createUserRANDOM());
        PostCreateBindingModel postCreateBindingModel = new PostCreateBindingModel();
        Principal principal = () -> creator.getUsername();

        PostCreateBindingModel result = this.postBindingModelsFillers.fill(postCreateBindingModel,user.getUsername(),principal);

        assertEquals(user.getUsername(),result.getUser().getUsername());
        assertEquals(creator.getUsername(),result.getCreator().getUsername());

    }

    @Test
    public void fill_withValidData_shouldNotChangeTheBindingModel(){
        User user = this.userRepository.save(this.entityFactory.createUserRANDOM());
        User creator = this.userRepository.save(this.entityFactory.createUserRANDOM());
        PostCreateBindingModel postCreateBindingModel = new PostCreateBindingModel();
        postCreateBindingModel.setContent("content");
        Principal principal = () -> creator.getUsername();

        PostCreateBindingModel result = this.postBindingModelsFillers.fill(postCreateBindingModel,user.getUsername(),principal);

        assertEquals(user.getUsername(),result.getUser().getUsername());
        assertEquals(creator.getUsername(),result.getCreator().getUsername());
        assertEquals(postCreateBindingModel.getContent(),result.getContent());

    }

}
