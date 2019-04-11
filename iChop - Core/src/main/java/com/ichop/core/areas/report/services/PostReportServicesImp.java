package com.ichop.core.areas.report.services;

import com.ichop.core.areas.post.exceptions.PostNotFoundException;
import com.ichop.core.areas.report.domain.entities.PostReport;
import com.ichop.core.areas.report.domain.models.binding.PostReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.PostReportServiceModel;
import com.ichop.core.areas.report.exceptions.ReportNotFoundException;
import com.ichop.core.areas.report.repositories.PostReportRepository;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostReportServicesImp extends BaseReportServices<PostReport, PostReportRepository> implements PostReportServices {

    @Autowired
    public PostReportServicesImp(ModelMapper modelMapper, PostReportRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public PostReportServiceModel create(PostReportCreateBindingModel bindingModel) {

        if(bindingModel.getPost() == null){
            throw new PostNotFoundException();
        }

        if(bindingModel.getUser() == null){
            throw new UserNotFoundException();
        }

        PostReportServiceModel postReport = this.modelMapper.map(bindingModel,PostReportServiceModel.class);
        postReport.setReportDate(LocalDateTime.now());

        PostReportServiceModel result = this.save(postReport,PostReportServiceModel.class);

        return result;
    }

    @Override
    public PostReportServiceModel findById(String id) {
        return super.findById(id,PostReportServiceModel.class);
    }

    @Override
    public void deleteByModel(PostReportServiceModel postReport) {

        if(postReport == null || !this.existsById(postReport.getId())){
            throw new ReportNotFoundException();
        }

        this.repository.deleteById(postReport.getId());
    }

    @Override
    public Page<PostReportServiceModel> findAll(Pageable pageable) {
        return this.findAll(PostReportServiceModel.class,pageable);
    }

}

