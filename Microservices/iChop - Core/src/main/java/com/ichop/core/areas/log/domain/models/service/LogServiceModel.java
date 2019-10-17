package com.ichop.core.areas.log.domain.models.service;

import com.ichop.core.base.BaseServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class LogServiceModel extends BaseServiceModel {

    private String id;
    private LocalDateTime happenedOn;
    private String message;

}
