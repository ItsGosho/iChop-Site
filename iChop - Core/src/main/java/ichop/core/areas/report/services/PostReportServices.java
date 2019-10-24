package ichop.core.areas.report.services;

import ichop.core.areas.post.domain.models.service.PostServiceModel;
import ichop.core.areas.report.domain.models.binding.PostReportCreateBindingModel;
import ichop.core.areas.report.domain.models.service.PostReportServiceModel;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostReportServices {

    /*
     *
     * Creates post report.
     * @throws PostNotFoundException if the post is null
     * @throws UserNotFoundException if the user is null
     * @returns PostReportServiceModel which is always valid
     *
     * */
    PostReportServiceModel create(PostReportCreateBindingModel bindingModel);


    /*
     *
     * Finds post report by id
     * @returns PostReportServiceModel which is null if post report is not found
     *
     * */
    PostReportServiceModel findById(String id);

    /*
     *
     * Deletes post report by model
     * @throws ReportNotFoundException if the post report is null or doesn't exist
     *
     * */
    void deleteByModel(PostReportServiceModel postReport);


    /*
     *
     * Finds all reports.
     *
     * */
    Page<PostReportServiceModel> findAll(Pageable pageable);

    boolean isReportedByUser(UserServiceModel user, PostServiceModel post);
}
