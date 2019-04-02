package com.ichop.core.areas.report.services;

import com.ichop.core.areas.post.domain.models.service.PostServiceModel;
import com.ichop.core.areas.report.domain.models.service.PostReportServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

public interface PostReportServices {


    PostReportServiceModel create(PostServiceModel post, UserServiceModel user, String reason);

}
