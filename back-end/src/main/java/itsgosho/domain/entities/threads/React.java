package itsgosho.domain.entities.threads;

import itsgosho.domain.entities.BaseEntity;
import itsgosho.domain.entities.users.User;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class React extends BaseEntity {

    @ManyToOne
    private User user;

    @Enumerated(value = EnumType.STRING)
    private ReactionType reactionType;
}
