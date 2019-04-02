package com.ichop.core.areas.report.services;

import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.report.domain.entities.CommentReport;
import com.ichop.core.areas.report.domain.models.service.CommentReportServiceModel;
import com.ichop.core.areas.report.repositories.CommentReportRepository;
import com.ichop.core.areas.thread.exceptions.CommentNotFoundException;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentReportServicesImp extends BaseReportServices<CommentReport, CommentReportRepository> implements CommentReportServices {

    @Autowired
    public CommentReportServicesImp(ModelMapper modelMapper, CommentReportRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public CommentReportServiceModel create(CommentServiceModel comment, UserServiceModel user, String reason) {

        if(comment == null){
            throw new CommentNotFoundException();
        }

        if(user == null){
            throw new UserNotFoundException();
        }

        CommentReportServiceModel commentReport = new CommentReportServiceModel();
        commentReport.setComment(comment);
        commentReport.setUser(user);
        commentReport.setReason(reason);
        commentReport.setReportDate(LocalDateTime.now());

        CommentReportServiceModel result = super.save(commentReport,CommentReportServiceModel.class);

        return result;
    }
}
