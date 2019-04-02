package com.ichop.core.areas.report.services;

import com.ichop.core.areas.post.domain.entities.PostReport;
import com.ichop.core.areas.post.domain.models.service.PostServiceModel;
import com.ichop.core.areas.post.exceptions.PostNotFoundException;
import com.ichop.core.areas.report.domain.models.service.PostReportServiceModel;
import com.ichop.core.areas.report.repositories.PostReportRepository;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostReportServicesImp extends BaseReportServices<PostReport, PostReportRepository> implements PostReportServices {


    @Autowired
    public PostReportServicesImp(ModelMapper modelMapper, PostReportRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public PostReportServiceModel create(PostServiceModel post, UserServiceModel user, String reason) {

        if(post == null){
            throw new PostNotFoundException();
        }

        if(user == null){
            throw new UserNotFoundException();
        }

        PostReportServiceModel postReport = new PostReportServiceModel();
        postReport.setPost(post);
        postReport.setUser(user);
        postReport.setReason(reason);
        postReport.setReportDate(LocalDateTime.now());

        PostReportServiceModel result = super.save(postReport,PostReportServiceModel.class);

        return result;
    }
}
