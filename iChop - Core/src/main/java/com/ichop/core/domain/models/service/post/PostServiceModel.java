package com.ichop.core.domain.models.service.post;

import com.ichop.core.domain.models.service.BaseServiceModel;
import com.ichop.core.domain.models.service.report.PostReportServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
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
