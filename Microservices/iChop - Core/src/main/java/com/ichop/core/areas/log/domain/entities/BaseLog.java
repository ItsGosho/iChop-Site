package com.ichop.core.areas.log.domain.entities;

import com.ichop.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseLog extends BaseEntity {

    @Column(name = "happened_on",nullable = false,updatable = false)
    private LocalDateTime happenedOn;

    @Lob
    @Column(name = "message" ,nullable = false,updatable = false)
    private String message;


}
