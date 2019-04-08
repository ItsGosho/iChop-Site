package com.ichop.core.areas.report.services;

import com.ichop.core.areas.report.domain.models.binding.PostReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.PostReportServiceModel;

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

}
