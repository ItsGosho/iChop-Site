package ichop.domain.entities.log;

import ichop.domain.entities.users.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users_logs")
public class UserLog extends BaseLog {

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private UserLogType logType;

}
