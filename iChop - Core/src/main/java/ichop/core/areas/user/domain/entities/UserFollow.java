package ichop.core.areas.user.domain.entities;

import ichop.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users_follows")
public class UserFollow extends BaseEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private User follower;

    @Column(nullable = false)
    private LocalDateTime since;

}
