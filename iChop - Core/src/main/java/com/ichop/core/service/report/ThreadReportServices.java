package com.ichop.core.service.report;

import com.ichop.core.domain.models.service.report.ThreadReportServiceModel;
import com.ichop.core.domain.models.service.thread.ThreadServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;

public interface ThreadReportServices {

    ThreadReportServiceModel createReport(ThreadServiceModel thread, UserServiceModel user, String reason);

}
