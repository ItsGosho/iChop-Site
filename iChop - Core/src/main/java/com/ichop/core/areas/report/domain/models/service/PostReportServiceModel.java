package com.ichop.core.areas.report.domain.models.service;

import com.ichop.core.base.BaseServiceModel;
import com.ichop.core.areas.post.domain.models.service.PostServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostReportServiceModel extends BaseServiceModel {

    private UserServiceModel user;
    private String reason;
    private LocalDateTime reportDate;
    private PostServiceModel post;

}
