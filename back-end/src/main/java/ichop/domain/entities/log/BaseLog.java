package ichop.domain.entities.log;

import ichop.domain.entities.BaseEntity;
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

    @Column(nullable = false)
    private LocalDateTime happenedOn;

    @Lob
    @Column(nullable = false)
    private String message;

}
