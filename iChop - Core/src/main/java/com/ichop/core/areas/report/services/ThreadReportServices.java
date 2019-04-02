package com.ichop.core.areas.report.services;

import com.ichop.core.areas.report.domain.models.service.ThreadReportServiceModel;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

public interface ThreadReportServices {

    ThreadReportServiceModel create(ThreadServiceModel thread, UserServiceModel user, String reason);

}
