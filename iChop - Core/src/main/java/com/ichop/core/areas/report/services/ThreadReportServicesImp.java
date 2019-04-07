package com.ichop.core.areas.report.services;

import com.ichop.core.areas.report.domain.entities.ThreadReport;
import com.ichop.core.areas.report.domain.models.binding.ThreadReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.ThreadReportServiceModel;
import com.ichop.core.areas.report.repositories.ThreadReportRepository;
import com.ichop.core.areas.thread.exceptions.ThreadNotFoundException;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ThreadReportServicesImp extends BaseReportServices<ThreadReport, ThreadReportRepository> implements ThreadReportServices {


    @Autowired
    public ThreadReportServicesImp(ModelMapper modelMapper, ThreadReportRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public ThreadReportServiceModel create(ThreadReportCreateBindingModel bindingModel) {

        if(bindingModel.getThread() == null){
            throw new ThreadNotFoundException();
        }

        if(bindingModel.getUser() == null){
            throw new UserNotFoundException();
        }

        ThreadReportServiceModel threadReport = this.modelMapper.map(bindingModel,ThreadReportServiceModel.class);
        threadReport.setReportDate(LocalDateTime.now());

        ThreadReportServiceModel result = this.save(threadReport,ThreadReportServiceModel.class);

        return result;
    }
}
