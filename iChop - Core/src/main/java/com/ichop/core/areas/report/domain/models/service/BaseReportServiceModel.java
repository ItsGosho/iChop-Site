package com.ichop.core.areas.report.domain.models.service;

import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.base.BaseServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseReportServiceModel extends BaseServiceModel {

    private UserServiceModel user;
    private LocalDateTime reportDate;
    private String reason;

}
