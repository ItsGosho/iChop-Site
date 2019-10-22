package com.ichop.core.areas.reaction.web.fillers;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.comment.domain.entities.Comment;
import com.ichop.core.areas.comment.repositories.CommentRepository;
import com.ichop.core.areas.comment.services.CommentServices;
import com.ichop.core.areas.comment.services.CommentServicesImp;
import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.domain.models.binding.CommentReactionCreateBindingModel;
import com.ichop.core.areas.reaction.domain.models.binding.ThreadReactionCreateBindingModel;
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
public class ReactionBindingModelsFillersIntegrationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private CommentRepository commentRepository;

    private UserServices userServices;
    private ThreadServices threadServices;
    private CommentServices commentServices;
    private EntityFactory entityFactory;
    private ModelMapper modelMapper;

    private ReactionBindingModelsFillers reactionBindingModelsFillers;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.entityFactory = new EntityFactory();

        this.threadServices = new ThreadServicesImp(this.modelMapper, this.threadRepository);
        this.commentServices = new CommentServicesImp(this.modelMapper, this.commentRepository);
        this.userServices = new UserServicesImp(null, this.modelMapper, this.userRepository, null, null, null, null);
        this.reactionBindingModelsFillers = new ReactionBindingModelsFillers(this.userServices, this.threadServices, this.commentServices);
    }

    @Test
    public void fillThreadReactionCreateBindingModel_withNotExistingUser_shouldSetUserToNull() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Thread thread = this.threadRepository.save(this.entityFactory.createThread(user));
        ReactionType reaction = ReactionType.LIKE;
        Principal principal = () -> "notExistingUserUsername";

        ThreadReactionCreateBindingModel bindingModel = new ThreadReactionCreateBindingModel();

        ThreadReactionCreateBindingModel result = this.reactionBindingModelsFillers.fill(bindingModel, thread.getId(), reaction.name(), principal);

        assertNull(result.getUser());
        assertEquals(thread.getId(), result.getThread().getId());
        assertEquals(reaction.name().toUpperCase(), result.getReactionType().name().toUpperCase());

    }

    @Test
    public void fillThreadReactionCreateBindingModel_withNotExistingThread_shouldSetThreadToNull() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        ReactionType reaction = ReactionType.LIKE;
        Principal principal = () -> user.getUsername();

        ThreadReactionCreateBindingModel bindingModel = new ThreadReactionCreateBindingModel();

        ThreadReactionCreateBindingModel result = this.reactionBindingModelsFillers.fill(bindingModel, "notExisting", reaction.name(), principal);

        assertNull(result.getThread());
        assertEquals(user.getUsername(), result.getUser().getUsername());
        assertEquals(reaction.name().toUpperCase(), result.getReactionType().name().toUpperCase());

    }

    @Test
    public void fillThreadReactionCreateBindingModel_withNotExistingReaction_shouldSetReactionToNull() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Thread thread = this.threadRepository.save(this.entityFactory.createThread(user));
        Principal principal = () -> user.getUsername();

        ThreadReactionCreateBindingModel bindingModel = new ThreadReactionCreateBindingModel();

        ThreadReactionCreateBindingModel result = this.reactionBindingModelsFillers.fill(bindingModel, thread.getId(), "notExistingReaction", principal);

        assertNull(result.getReactionType());
        assertEquals(thread.getId(), result.getThread().getId());
        assertEquals(user.getId(), result.getUser().getId());

    }

    @Test
    public void fillThreadReactionCreateBindingModel_withReactionNamePassedAsLowerCase_shouldSetReaction() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Thread thread = this.threadRepository.save(this.entityFactory.createThread(user));
        Principal principal = () -> user.getUsername();

        ThreadReactionCreateBindingModel bindingModel = new ThreadReactionCreateBindingModel();

        ThreadReactionCreateBindingModel result = this.reactionBindingModelsFillers.fill(bindingModel, thread.getId(), "like", principal);

        assertEquals(ReactionType.LIKE.name(), result.getReactionType().name().toUpperCase());
        assertEquals(thread.getId(), result.getThread().getId());
        assertEquals(user.getId(), result.getUser().getId());

    }

    @Test
    public void fillThreadReactionCreateBindingModel_withValidData_shouldSetAllParameters() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Thread thread = this.threadRepository.save(this.entityFactory.createThread(user));
        ReactionType reaction = ReactionType.LIKE;
        Principal principal = () -> user.getUsername();

        ThreadReactionCreateBindingModel bindingModel = new ThreadReactionCreateBindingModel();

        ThreadReactionCreateBindingModel result = this.reactionBindingModelsFillers.fill(bindingModel, thread.getId(), reaction.name(), principal);

        assertEquals(reaction.name().toUpperCase(), result.getReactionType().name().toUpperCase());
        assertEquals(thread.getId(), result.getThread().getId());
        assertEquals(user.getId(), result.getUser().getId());

    }


    @Test
    public void fillCommentReactionCreateBindingModel_withNotExistingUser_shouldSetUserToNull() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Comment comment = this.commentRepository.save(this.entityFactory.createComment(user));
        ReactionType reaction = ReactionType.LIKE;
        Principal principal = () -> "notExistingUserUsername";

        CommentReactionCreateBindingModel bindingModel = new CommentReactionCreateBindingModel();

        CommentReactionCreateBindingModel result = this.reactionBindingModelsFillers.fill(bindingModel, comment.getId(), reaction.name(), principal);

        assertNull(result.getUser());
        assertEquals(comment.getId(), result.getComment().getId());
        assertEquals(reaction.name().toUpperCase(), result.getReactionType().name().toUpperCase());

    }

    @Test
    public void fillCommentReactionCreateBindingModel_withNotExistingComment_shouldSetCommentToNull() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        ReactionType reaction = ReactionType.LIKE;
        Principal principal = () -> user.getUsername();

        CommentReactionCreateBindingModel bindingModel = new CommentReactionCreateBindingModel();

        CommentReactionCreateBindingModel result = this.reactionBindingModelsFillers.fill(bindingModel, "notExisting", reaction.name(), principal);

        assertNull(result.getComment());
        assertEquals(user.getUsername(), result.getUser().getUsername());
        assertEquals(reaction.name().toUpperCase(), result.getReactionType().name().toUpperCase());

    }

    @Test
    public void fillCommentReactionCreateBindingModel_withNotExistingReaction_shouldSetReactionToNull() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Comment comment = this.commentRepository.save(this.entityFactory.createComment(user));
        Principal principal = () -> user.getUsername();

        CommentReactionCreateBindingModel bindingModel = new CommentReactionCreateBindingModel();

        CommentReactionCreateBindingModel result = this.reactionBindingModelsFillers.fill(bindingModel, comment.getId(), "notExistingReaction", principal);

        assertNull(result.getReactionType());
        assertEquals(comment.getId(), result.getComment().getId());
        assertEquals(user.getId(), result.getUser().getId());

    }

    @Test
    public void fillCommentReactionCreateBindingModel_withReactionNamePassedAsLowerCase_shouldSetReaction() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Comment comment = this.commentRepository.save(this.entityFactory.createComment(user));
        Principal principal = () -> user.getUsername();

        CommentReactionCreateBindingModel bindingModel = new CommentReactionCreateBindingModel();

        CommentReactionCreateBindingModel result = this.reactionBindingModelsFillers.fill(bindingModel, comment.getId(), "like", principal);

        assertEquals(ReactionType.LIKE.name(), result.getReactionType().name().toUpperCase());
        assertEquals(comment.getId(), result.getComment().getId());
        assertEquals(user.getId(), result.getUser().getId());

    }

    @Test
    public void fillCommentReactionCreateBindingModel_withValidData_shouldSetAllParameters() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Comment comment = this.commentRepository.save(this.entityFactory.createComment(user));
        ReactionType reaction = ReactionType.LIKE;
        Principal principal = () -> user.getUsername();

        CommentReactionCreateBindingModel bindingModel = new CommentReactionCreateBindingModel();

        CommentReactionCreateBindingModel result = this.reactionBindingModelsFillers.fill(bindingModel, comment.getId(), reaction.name(), principal);

        assertEquals(reaction.name().toUpperCase(), result.getReactionType().name().toUpperCase());
        assertEquals(comment.getId(), result.getComment().getId());
        assertEquals(user.getId(), result.getUser().getId());

    }

}
