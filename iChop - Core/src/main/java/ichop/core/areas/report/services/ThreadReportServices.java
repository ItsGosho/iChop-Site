package ichop.core.areas.report.services;

import ichop.core.areas.report.domain.models.binding.ThreadReportCreateBindingModel;
import ichop.core.areas.report.domain.models.service.ThreadReportServiceModel;
import ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ThreadReportServices {

    /*
     *
     * Creates thread report.
     * @throws ThreadNotFoundException if the thread is null
     * @throws UserNotFoundException if the user is null
     * @returns ThreadReportServiceModel which is always valid
     *
     * */
    ThreadReportServiceModel create(ThreadReportCreateBindingModel bindingModel);

    /*
    *
    * Finds thread report by id
    * @returns ThreadReportServiceModel which is null if thread report is not found
    *
    * */
    ThreadReportServiceModel findById(String id);

    /*
    *
    * Deletes thread report by model
    * @throws ReportNotFoundException if the thread report is null or doesn't exist
    *
    * */
    void deleteByModel(ThreadReportServiceModel threadReport);


    /*
    *
    * Finds all reports.
    *
    * */
    Page<ThreadReportServiceModel> findAll(Pageable pageable);

    /*
    *
    * Check if the user already reported the provided thread
    *
    * */
    boolean isReportedByUser(UserServiceModel user, ThreadServiceModel thread);
}
