package com.ichop.core.areas.report.domain.models.binding;

import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseReportCreateBindingModel {

    private String reason;
    private UserServiceModel user;

}
