package ichop.users.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "UserFollow")
@Table(name = "users_follow")
public class UserFollow extends BaseEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private User follower;

    private LocalDateTime since = LocalDateTime.now();

}
