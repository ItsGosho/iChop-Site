package com.ichop.core.areas.comment.web.fillers;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.comment.domain.models.binding.CommentCreateBindingModel;
import com.ichop.core.areas.thread.domain.entities.Thread;
import com.ichop.core.areas.thread.repositories.ThreadRepository;
import com.ichop.core.areas.thread.services.ThreadServices;
import com.ichop.core.areas.thread.services.ThreadServicesImp;
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
public class CommentBindingModelsFillersIntegrationTests {

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private UserRepository userRepository;

    private UserServices userServices;
    private ThreadServices threadServices;
    private EntityFactory entityFactory;
    private ModelMapper modelMapper;

    private CommentBindingModelsFillers commentBindingModelsFillers;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.entityFactory = new EntityFactory();

        this.threadServices = new ThreadServicesImp(this.modelMapper, this.threadRepository);
        this.userServices = new UserServicesImp(null, this.modelMapper, this.userRepository, null, null, null, null);
        this.commentBindingModelsFillers = new CommentBindingModelsFillers(this.threadServices,this.userServices);
    }

    @Test
    public void fill_withNotExistingUser_shouldSetCreatorNull() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Thread thread = this.threadRepository.save(this.entityFactory.createThread(user));
        Principal principal = () -> "notExistingUsername";

        CommentCreateBindingModel passedModel = new CommentCreateBindingModel();

        CommentCreateBindingModel result = this.commentBindingModelsFillers.fill(passedModel, thread.getId(),principal);

        assertEquals(thread.getId(),result.getThread().getId());
        assertNull(result.getCreator());
    }

    @Test
    public void fill_withNotExistingThread_shouldSetThreadNull() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Principal principal = user::getUsername;

        CommentCreateBindingModel passedModel = new CommentCreateBindingModel();

        CommentCreateBindingModel result = this.commentBindingModelsFillers.fill(passedModel, "notExistingThreadById",principal);

        assertEquals(user.getId(),result.getCreator().getId());
        assertNull(result.getThread());
    }

    @Test
    public void fill_withNotExistingThreadAndUser_shouldSetThreadAndCreatorNull() {
        Principal principal = () -> "notExistingUserByUsername";

        CommentCreateBindingModel passedModel = new CommentCreateBindingModel();

        CommentCreateBindingModel result = this.commentBindingModelsFillers.fill(passedModel, "notExistingThreadById",principal);

        assertNull(result.getThread());
        assertNull(result.getCreator());
    }

    @Test
    public void fill_withValidParameters_shouldNOTChangeThePassedBindingModel() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Thread thread = this.threadRepository.save(this.entityFactory.createThread(user));
        Principal principal = () -> user.getUsername();

        CommentCreateBindingModel passedModel = new CommentCreateBindingModel();
        passedModel.setContent("content");

        CommentCreateBindingModel result = this.commentBindingModelsFillers.fill(passedModel, thread.getId(),principal);

        assertEquals(passedModel.getContent(),result.getContent());
    }

}
