package com.ichop.core.service.report;

import com.ichop.core.domain.models.service.comment.CommentServiceModel;
import com.ichop.core.domain.models.service.report.CommentReportServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;

public interface CommentReportServices {

    CommentReportServiceModel createReport(CommentServiceModel comment, UserServiceModel user, String reason);

}
