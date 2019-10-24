package ichop.core.areas.report.services;

import ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import ichop.core.areas.report.domain.models.binding.CommentReportCreateBindingModel;
import ichop.core.areas.report.domain.models.service.CommentReportServiceModel;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentReportServices {

    /*
    *
    * Creates comment report.
    * @throws CommentNotFoundException if the comment is null
    * @throws UserNotFoundException if the user is null
    * @returns CommentReportServiceModel which is always valid
    *
    * */
    CommentReportServiceModel create(CommentReportCreateBindingModel bindingModel);

    /*
     *
     * Finds comment report by id
     * @returns CommentReportServiceModel which is null if comment report is not found
     *
     * */
    CommentReportServiceModel findById(String id);

    /*
     *
     * Deletes comment report by model
     * @throws ReportNotFoundException if the comment report is null or doesn't exist
     *
     * */
    void deleteByModel(CommentReportServiceModel commentReport);

    /*
     *
     * Finds all reports.
     *
     * */
    Page<CommentReportServiceModel> findAll(Pageable pageable);

    boolean isReportedByUser(UserServiceModel user, CommentServiceModel comment);
}
