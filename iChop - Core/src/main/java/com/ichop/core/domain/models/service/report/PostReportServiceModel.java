package com.ichop.core.domain.models.service.report;

import com.ichop.core.domain.models.service.BaseServiceModel;
import com.ichop.core.domain.models.service.post.PostServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
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
