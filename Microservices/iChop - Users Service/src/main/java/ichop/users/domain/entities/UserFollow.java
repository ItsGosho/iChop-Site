package ichop.users.domain.entities;

import ichop.users.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document("users_follow")
public class UserFollow extends BaseEntity {

    @DBRef
    private User user;

    @DBRef
    private User follower;

    private LocalDateTime since = LocalDateTime.now();

}
