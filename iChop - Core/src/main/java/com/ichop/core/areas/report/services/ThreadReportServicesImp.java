package com.ichop.core.areas.report.services;

import com.ichop.core.areas.report.domain.entities.ThreadReport;
import com.ichop.core.areas.report.domain.models.service.ThreadReportServiceModel;
import com.ichop.core.areas.report.repositories.ThreadReportRepository;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.exceptions.ThreadNotFoundException;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
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
    public ThreadReportServiceModel create(ThreadServiceModel thread, UserServiceModel user, String reason) {

        if(thread == null){
            throw new ThreadNotFoundException();
        }

        if(user == null){
            throw new UserNotFoundException();
        }

        ThreadReportServiceModel threadReport = new ThreadReportServiceModel();
        threadReport.setThread(thread);
        threadReport.setUser(user);
        threadReport.setReason(reason);
        threadReport.setReportDate(LocalDateTime.now());

        ThreadReportServiceModel result = super.save(threadReport,ThreadReportServiceModel.class);

        return result;
    }
}
