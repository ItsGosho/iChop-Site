package com.ichop.core.areas.report.services;

import com.ichop.core.areas.report.domain.models.binding.CommentReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.CommentReportServiceModel;

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

}
