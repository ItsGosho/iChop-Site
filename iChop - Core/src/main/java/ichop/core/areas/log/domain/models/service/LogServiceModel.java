package ichop.core.areas.log.domain.models.service;

import ichop.core.base.BaseServiceModel;
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
