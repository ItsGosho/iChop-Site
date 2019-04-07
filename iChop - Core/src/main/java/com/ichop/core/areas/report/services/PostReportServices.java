package com.ichop.core.areas.report.services;

import com.ichop.core.areas.report.domain.models.binding.PostReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.PostReportServiceModel;

public interface PostReportServices {


    PostReportServiceModel create(PostReportCreateBindingModel bindingModel);

}
