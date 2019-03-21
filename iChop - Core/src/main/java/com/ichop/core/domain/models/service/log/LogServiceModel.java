package com.ichop.core.domain.models.service.log;

import com.ichop.core.domain.models.service.BaseServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LogServiceModel extends BaseServiceModel {

    private String id;
    private LocalDateTime happenedOn;
    private String message;

}
