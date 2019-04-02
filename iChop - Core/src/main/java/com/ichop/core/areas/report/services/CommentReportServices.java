package com.ichop.core.areas.report.services;

import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.report.domain.models.service.CommentReportServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

public interface CommentReportServices {

    CommentReportServiceModel create(CommentServiceModel comment, UserServiceModel user, String reason);

}
