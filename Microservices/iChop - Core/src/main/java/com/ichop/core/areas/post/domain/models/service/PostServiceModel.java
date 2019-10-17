package com.ichop.core.areas.post.domain.models.service;

import com.ichop.core.base.BaseServiceModel;
import com.ichop.core.areas.report.domain.models.service.PostReportServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostServiceModel extends BaseServiceModel {

    private UserServiceModel user;
    private UserServiceModel creator;
    private String content;
    private LocalDateTime createdOn;
    private List<PostReportServiceModel> reports;

}
