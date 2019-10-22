package com.ichop.core.areas.report.services;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.comment.domain.entities.Comment;
import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.comment.repositories.CommentRepository;
import com.ichop.core.areas.report.domain.models.binding.CommentReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.CommentReportServiceModel;
import com.ichop.core.areas.report.repositories.CommentReportRepository;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class CommentReportServicesIntegrationTests {

    @Autowired
    private CommentReportRepository commentReportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;


    private ModelMapper modelMapper;
    private EntityFactory entityFactory;
    private CommentReportServices commentReportServices;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.entityFactory = new EntityFactory();

        this.commentReportServices = new CommentReportServicesImp(this.modelMapper, this.commentReportRepository);
    }

    @Test
    public void create_withValidData_shouldReturnValidResult() {
        User user = this.entityFactory.createUser();
        this.userRepository.save(user);
        Comment comment = this.entityFactory.createComment();
        comment.setCreator(user);
        this.commentRepository.save(comment);

        CommentReportCreateBindingModel bindingModel = new CommentReportCreateBindingModel();
        bindingModel.setReason("reason");
        bindingModel.setComment(this.modelMapper.map(comment, CommentServiceModel.class));
        bindingModel.setUser(this.modelMapper.map(user, UserServiceModel.class));

        CommentReportServiceModel result = this.commentReportServices.create(bindingModel);

        assertEquals(result.getComment().getContent(),comment.getContent());
        assertEquals(result.getReason(),"reason");
        assertEquals(result.getUser().getUsername(),user.getUsername());
        assertTrue(result.getReportDate().isBefore(LocalDateTime.now()));
    }

    @Test
    public void findById_withNotExistingCommentReport_shouldReturnNull(){
        CommentReportServiceModel commentReportServiceModel = this.commentReportServices.findById("id");

        assertNull(commentReportServiceModel);
    }

    @Test
    public void findAll_withNotPresentReports_shouldReturn0(){
        Pageable pageable = PageRequest.of(10,10);

        Page<CommentReportServiceModel> result = this.commentReportServices.findAll(pageable);

        assertEquals(0,result.getNumberOfElements());
    }

}
