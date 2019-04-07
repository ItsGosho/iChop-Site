package com.ichop.core.areas.report.services;

import com.ichop.core.areas.report.domain.models.binding.CommentReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.CommentReportServiceModel;

public interface CommentReportServices {

    CommentReportServiceModel create(CommentReportCreateBindingModel bindingModel);

}
