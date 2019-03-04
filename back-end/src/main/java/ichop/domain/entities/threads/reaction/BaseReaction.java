package ichop.domain.entities.threads.reaction;

import ichop.domain.entities.BaseEntity;
import ichop.domain.entities.users.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseReaction extends BaseEntity {

    @ManyToOne(optional = false)
    private User user;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ReactionType reactionType;

}
