package com.ichop.core.areas.report.services;

import com.ichop.core.areas.report.domain.models.binding.ThreadReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.ThreadReportServiceModel;

public interface ThreadReportServices {

    ThreadReportServiceModel create(ThreadReportCreateBindingModel bindingModel);

}
