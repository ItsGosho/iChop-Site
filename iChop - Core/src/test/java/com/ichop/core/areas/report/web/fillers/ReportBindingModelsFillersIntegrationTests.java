package com.ichop.core.areas.report.web.fillers;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.comment.domain.entities.Comment;
import com.ichop.core.areas.comment.repositories.CommentRepository;
import com.ichop.core.areas.comment.services.CommentServices;
import com.ichop.core.areas.comment.services.CommentServicesImp;
import com.ichop.core.areas.post.domain.entities.Post;
import com.ichop.core.areas.post.repositories.PostRepository;
import com.ichop.core.areas.post.services.PostServices;
import com.ichop.core.areas.post.services.PostServicesImp;
import com.ichop.core.areas.report.domain.models.binding.CommentReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.binding.PostReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.binding.ThreadReportCreateBindingModel;
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
public class ReportBindingModelsFillersIntegrationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    private UserServices userServices;
    private ThreadServices threadServices;
    private CommentServices commentServices;
    private PostServices postServices;
    private EntityFactory entityFactory;
    private ModelMapper modelMapper;

    private ReportBindingModelsFillers reportBindingModelsFillers;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.entityFactory = new EntityFactory();

        this.threadServices = new ThreadServicesImp(this.modelMapper, this.threadRepository);
        this.commentServices = new CommentServicesImp(this.modelMapper, this.commentRepository);
        this.postServices = new PostServicesImp(this.modelMapper, this.postRepository);
        this.userServices = new UserServicesImp(null, this.modelMapper, this.userRepository, null, null, null, null);
        this.reportBindingModelsFillers = new ReportBindingModelsFillers(this.userServices, this.commentServices, this.threadServices,this.postServices);
    }

    @Test
    public void fillThreadReportCreateBindingModel_withNotExistingUser_shouldSetUserToNull() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Thread thread = this.threadRepository.save(this.entityFactory.createThread(user));
        Principal principal = () -> "notExistingUserUsername";

        ThreadReportCreateBindingModel bindingModel = new ThreadReportCreateBindingModel();

        ThreadReportCreateBindingModel result = this.reportBindingModelsFillers.fill(bindingModel, thread.getId(), principal);

        assertNull(result.getUser());
        assertEquals(thread.getId(), result.getThread().getId());

    }

    @Test
    public void fillThreadReportCreateBindingModel_withNotExistingThread_shouldSetThreadToNull() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Principal principal = () -> user.getUsername();

        ThreadReportCreateBindingModel bindingModel = new ThreadReportCreateBindingModel();

        ThreadReportCreateBindingModel result = this.reportBindingModelsFillers.fill(bindingModel, "notExistingThreadId", principal);

        assertNull(result.getThread());
        assertEquals(user.getId(), result.getUser().getId());

    }

    @Test
    public void fillThreadReportCreateBindingModel_withValidData_shouldSetUserAndThread() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Thread thread = this.threadRepository.save(this.entityFactory.createThread(user));
        Principal principal = () -> user.getUsername();

        ThreadReportCreateBindingModel bindingModel = new ThreadReportCreateBindingModel();

        ThreadReportCreateBindingModel result = this.reportBindingModelsFillers.fill(bindingModel, thread.getId(), principal);

        assertEquals(thread.getId(),result.getThread().getId());
        assertEquals(user.getId(), result.getUser().getId());

    }

    @Test
    public void fillThreadReportCreateBindingModel_withValidData_shouldNotChangeTheBindingModel() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Thread thread = this.threadRepository.save(this.entityFactory.createThread(user));
        Principal principal = () -> user.getUsername();

        ThreadReportCreateBindingModel bindingModel = new ThreadReportCreateBindingModel();
        bindingModel.setReason("reason");

        ThreadReportCreateBindingModel result = this.reportBindingModelsFillers.fill(bindingModel, thread.getId(), principal);

        assertEquals(thread.getId(),result.getThread().getId());
        assertEquals(user.getId(), result.getUser().getId());
        assertEquals(bindingModel.getReason(), result.getReason());

    }

    @Test
    public void fillCommentReportCreateBindingModel_withNotExistingUser_shouldSetUserToNull() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Comment comment = this.commentRepository.save(this.entityFactory.createComment(user));
        Principal principal = () -> "notExistingUserUsername";

        CommentReportCreateBindingModel bindingModel = new CommentReportCreateBindingModel();

        CommentReportCreateBindingModel result = this.reportBindingModelsFillers.fill(bindingModel, comment.getId(), principal);

        assertNull(result.getUser());
        assertEquals(comment.getId(), result.getComment().getId());

    }

    @Test
    public void fillCommentReportCreateBindingModel_withNotExistingComment_shouldSetCommentToNull() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Principal principal = () -> user.getUsername();

        CommentReportCreateBindingModel bindingModel = new CommentReportCreateBindingModel();

        CommentReportCreateBindingModel result = this.reportBindingModelsFillers.fill(bindingModel, "notExistingCommentId", principal);

        assertNull(result.getComment());
        assertEquals(user.getId(), result.getUser().getId());

    }

    @Test
    public void fillCommentReportCreateBindingModel_withValidData_shouldSetUserAndComment() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Comment comment = this.commentRepository.save(this.entityFactory.createComment(user));
        Principal principal = () -> user.getUsername();

        CommentReportCreateBindingModel bindingModel = new CommentReportCreateBindingModel();

        CommentReportCreateBindingModel result = this.reportBindingModelsFillers.fill(bindingModel, comment.getId(), principal);

        assertEquals(comment.getId(),result.getComment().getId());
        assertEquals(user.getId(), result.getUser().getId());

    }

    @Test
    public void fillCommentReportCreateBindingModel_withValidData_shouldNotChangeTheBindingModel() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Comment comment = this.commentRepository.save(this.entityFactory.createComment(user));
        Principal principal = () -> user.getUsername();

        CommentReportCreateBindingModel bindingModel = new CommentReportCreateBindingModel();
        bindingModel.setReason("reason");

        CommentReportCreateBindingModel result = this.reportBindingModelsFillers.fill(bindingModel, comment.getId(), principal);

        assertEquals(comment.getId(),result.getComment().getId());
        assertEquals(user.getId(), result.getUser().getId());
        assertEquals(bindingModel.getReason(), result.getReason());

    }




    @Test
    public void fillPostReportCreateBindingModel_withNotExistingUser_shouldSetUserToNull() {
        User user = this.userRepository.save(this.entityFactory.createUserRANDOM());
        User creator = this.userRepository.save(this.entityFactory.createUserRANDOM());
        Post post = this.postRepository.save(this.entityFactory.createPost(user,creator));
        Principal principal = () -> "notExistingUserUsername";

        PostReportCreateBindingModel bindingModel = new PostReportCreateBindingModel();

        PostReportCreateBindingModel result = this.reportBindingModelsFillers.fill(bindingModel, post.getId(), principal);

        assertNull(result.getUser());
        assertEquals(post.getId(), result.getPost().getId());

    }

    @Test
    public void fillPostReportCreateBindingModel_withNotExistingPost_shouldSetPostToNull() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Principal principal = () -> user.getUsername();

        PostReportCreateBindingModel bindingModel = new PostReportCreateBindingModel();

        PostReportCreateBindingModel result = this.reportBindingModelsFillers.fill(bindingModel, "notExistingPostId", principal);

        assertNull(result.getPost());
        assertEquals(user.getId(), result.getUser().getId());

    }

    @Test
    public void fillPostReportCreateBindingModel_withValidData_shouldSetUserAndPost() {
        User user = this.userRepository.save(this.entityFactory.createUserRANDOM());
        User creator = this.userRepository.save(this.entityFactory.createUserRANDOM());
        Post post = this.postRepository.save(this.entityFactory.createPost(user,creator));
        Principal principal = () -> user.getUsername();

        PostReportCreateBindingModel bindingModel = new PostReportCreateBindingModel();

        PostReportCreateBindingModel result = this.reportBindingModelsFillers.fill(bindingModel, post.getId(), principal);

        assertEquals(post.getId(),result.getPost().getId());
        assertEquals(user.getId(), result.getUser().getId());

    }

    @Test
    public void fillPostReportCreateBindingModel_withValidData_shouldNotChangeTheBindingModel() {
        User user = this.userRepository.save(this.entityFactory.createUserRANDOM());
        User creator = this.userRepository.save(this.entityFactory.createUserRANDOM());
        Post post = this.postRepository.save(this.entityFactory.createPost(user,creator));
        Principal principal = () -> user.getUsername();

        PostReportCreateBindingModel bindingModel = new PostReportCreateBindingModel();
        bindingModel.setReason("reason");

        PostReportCreateBindingModel result = this.reportBindingModelsFillers.fill(bindingModel, post.getId(), principal);

        assertEquals(post.getId(),result.getPost().getId());
        assertEquals(user.getId(), result.getUser().getId());
        assertEquals(bindingModel.getReason(), result.getReason());

    }

}
