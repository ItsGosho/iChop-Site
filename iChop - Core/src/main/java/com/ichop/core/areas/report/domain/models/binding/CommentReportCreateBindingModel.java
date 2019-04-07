package com.ichop.core.areas.report.domain.models.binding;

import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentReportCreateBindingModel extends BaseReportCreateBindingModel {

    private CommentServiceModel comment;

}
