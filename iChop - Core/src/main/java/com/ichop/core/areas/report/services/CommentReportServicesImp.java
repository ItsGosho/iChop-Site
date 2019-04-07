package com.ichop.core.areas.report.services;

import com.ichop.core.areas.report.domain.entities.CommentReport;
import com.ichop.core.areas.report.domain.models.binding.CommentReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.CommentReportServiceModel;
import com.ichop.core.areas.report.repositories.CommentReportRepository;
import com.ichop.core.areas.thread.exceptions.CommentNotFoundException;
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
    public CommentReportServiceModel create(CommentReportCreateBindingModel bindingModel) {

        if(bindingModel.getComment() == null){
            throw new CommentNotFoundException();
        }

        if(bindingModel.getUser() == null){
            throw new UserNotFoundException();
        }

        CommentReportServiceModel commentReport = this.modelMapper.map(bindingModel,CommentReportServiceModel.class);
        commentReport.setReportDate(LocalDateTime.now());

        CommentReportServiceModel result = this.save(commentReport,CommentReportServiceModel.class);

        return result;
    }
}
