package com.ichop.core.areas.report.services;

import com.ichop.core.areas.report.domain.models.binding.PostReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.PostReportServiceModel;
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

}
