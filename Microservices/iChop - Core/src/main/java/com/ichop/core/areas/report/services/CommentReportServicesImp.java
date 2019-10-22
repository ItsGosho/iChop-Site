package com.ichop.core.areas.report.services;

import com.ichop.core.areas.comment.domain.entities.Comment;
import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.comment.exceptions.CommentNotFoundException;
import com.ichop.core.areas.report.domain.entities.CommentReport;
import com.ichop.core.areas.report.domain.models.binding.CommentReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.CommentReportServiceModel;
import com.ichop.core.areas.report.exceptions.ReportAlreadyMadeException;
import com.ichop.core.areas.report.exceptions.ReportNotFoundException;
import com.ichop.core.areas.report.repositories.CommentReportRepository;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentReportServicesImp extends BaseReportServices<CommentReport, CommentReportRepository> implements CommentReportServices {

    @Autowired
    public CommentReportServicesImp(ModelMapper modelMapper, CommentReportRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public CommentReportServiceModel create(CommentReportCreateBindingModel bindingModel) {

        if(bindingModel.getComment() == null){
            throw new CommentNotFoundException();
        }

        if(bindingModel.getUser() == null){
            throw new UserNotFoundException();
        }

        if(this.isReportedByUser(bindingModel.getUser(),bindingModel.getComment())){
            throw new ReportAlreadyMadeException();
        }

        CommentReportServiceModel commentReport = this.modelMapper.map(bindingModel,CommentReportServiceModel.class);
        commentReport.setReportDate(LocalDateTime.now());

        CommentReportServiceModel result = this.save(commentReport,CommentReportServiceModel.class);

        return result;
    }

    @Override
    public CommentReportServiceModel findById(String id) {
        return super.findById(id,CommentReportServiceModel.class);
    }

    @Override
    public void deleteByModel(CommentReportServiceModel commentReport) {

        if(commentReport == null || !this.existsById(commentReport.getId())){
            throw new ReportNotFoundException();
        }

        this.repository.deleteById(commentReport.getId());
    }

    @Override
    public Page<CommentReportServiceModel> findAll(Pageable pageable) {
        return this.findAll(CommentReportServiceModel.class,pageable);
    }

    @Override
    public boolean isReportedByUser(UserServiceModel user, CommentServiceModel comment) {

        if (user == null) {
            throw new UserNotFoundException();
        }

        if (comment == null) {
            throw new CommentNotFoundException();
        }

        User entityUser = this.modelMapper.map(user, User.class);
        Comment entityComment = this.modelMapper.map(comment, Comment.class);

        boolean result = this.repository.isUserReportedComment(entityUser, entityComment);

        return result;
    }

}
