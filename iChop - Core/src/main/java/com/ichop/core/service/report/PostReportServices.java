package com.ichop.core.service.report;

import com.ichop.core.domain.models.service.post.PostServiceModel;
import com.ichop.core.domain.models.service.report.PostReportServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;

public interface PostReportServices {


    PostReportServiceModel createReport(PostServiceModel post, UserServiceModel user, String reason);

}
