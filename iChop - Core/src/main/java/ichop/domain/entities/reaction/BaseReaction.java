package ichop.domain.entities.reaction;

import ichop.domain.entities.BaseEntity;
import ichop.domain.entities.users.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseReaction extends BaseEntity {

    @ManyToOne(optional = false,targetEntity = User.class)
    private User user;

    @Column(name = "reaction_type",nullable = false,updatable = false)
    @Enumerated(value = EnumType.STRING)
    private ReactionType reactionType;

    @Column(name = "reaction_date",nullable = false,updatable = false)
    private LocalDateTime reactionDate;

}
