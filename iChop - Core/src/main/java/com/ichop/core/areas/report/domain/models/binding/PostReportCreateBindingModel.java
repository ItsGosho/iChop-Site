package com.ichop.core.areas.report.domain.models.binding;

import com.ichop.core.areas.post.domain.models.service.PostServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostReportCreateBindingModel extends BaseReportCreateBindingModel {

    private PostServiceModel post;

}
