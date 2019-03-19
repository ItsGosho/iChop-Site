package ichop.domain.entities.report;

import ichop.domain.entities.BaseEntity;
import ichop.domain.entities.users.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseReport extends BaseEntity {

    @ManyToOne(optional = false, targetEntity = User.class)
    private User user;

    @Column(name = "reason", nullable = false, updatable = false)
    private String reason;

    @Column(name = "report_date", nullable = false, updatable = false)
    private LocalDateTime reportDate;

}
