package ichop.domain.entities.threads;

import ichop.domain.entities.BaseEntity;
import ichop.domain.entities.users.User;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class React extends BaseEntity {

    @ManyToOne
    private User user;

    @Enumerated(value = EnumType.STRING)
    private ReactionType reactionType;


}
