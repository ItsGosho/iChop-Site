package com.ichop.core.areas.report.services;

import com.ichop.core.areas.report.domain.models.binding.ThreadReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.ThreadReportServiceModel;

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

}
