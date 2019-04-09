package com.ichop.core.areas.report.domain.models.service;

import com.ichop.core.areas.post.domain.models.service.PostServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostReportServiceModel extends BaseReportServiceModel {

    private PostServiceModel post;

}
