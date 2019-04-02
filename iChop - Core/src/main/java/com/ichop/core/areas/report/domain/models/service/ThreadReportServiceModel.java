package com.ichop.core.areas.report.domain.models.service;

import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.base.BaseServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ThreadReportServiceModel extends BaseServiceModel {

    private UserServiceModel user;
    private LocalDateTime reportDate;
    private String reason;
    private ThreadServiceModel thread;
}
