package ichop.core.areas.report.services;

import ichop.core.areas.report.domain.entities.ThreadReport;
import ichop.core.areas.report.domain.models.binding.ThreadReportCreateBindingModel;
import ichop.core.areas.report.domain.models.service.ThreadReportServiceModel;
import ichop.core.areas.report.exceptions.ReportAlreadyMadeException;
import ichop.core.areas.report.exceptions.ReportNotFoundException;
import ichop.core.areas.report.repositories.ThreadReportRepository;
import ichop.core.areas.thread.domain.entities.Thread;
import ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import ichop.core.areas.thread.exceptions.ThreadNotFoundException;
import ichop.core.areas.user.domain.entities.User;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.areas.user.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        if (bindingModel.getThread() == null) {
            throw new ThreadNotFoundException();
        }

        if (bindingModel.getUser() == null) {
            throw new UserNotFoundException();
        }

        if(this.isReportedByUser(bindingModel.getUser(),bindingModel.getThread())){
            throw new ReportAlreadyMadeException();
        }

        ThreadReportServiceModel threadReport = this.modelMapper.map(bindingModel, ThreadReportServiceModel.class);
        threadReport.setReportDate(LocalDateTime.now());

        ThreadReportServiceModel result = this.save(threadReport, ThreadReportServiceModel.class);

        return result;
    }

    @Override
    public ThreadReportServiceModel findById(String id) {
        return super.findById(id, ThreadReportServiceModel.class);
    }

    @Override
    public void deleteByModel(ThreadReportServiceModel threadReport) {

        if (threadReport == null || !this.existsById(threadReport.getId())) {
            throw new ReportNotFoundException();
        }

        this.repository.deleteById(threadReport.getId());
    }

    @Override
    public Page<ThreadReportServiceModel> findAll(Pageable pageable) {
        return this.findAll(ThreadReportServiceModel.class, pageable);
    }

    @Override
    public boolean isReportedByUser(UserServiceModel user, ThreadServiceModel thread) {

        if (user == null) {
            throw new UserNotFoundException();
        }

        if (thread == null) {
            throw new ThreadNotFoundException();
        }

        User entityUser = this.modelMapper.map(user, User.class);
        Thread entityThread = this.modelMapper.map(thread, Thread.class);

        boolean result = this.repository.isUserReportedThread(entityUser, entityThread);

        return result;
    }


}
