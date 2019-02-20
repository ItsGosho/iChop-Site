package ichop.domain.entities.threads;

import ichop.domain.entities.BaseEntity;
import ichop.domain.entities.users.User;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class React extends BaseEntity {

    @ManyToOne(optional = false)
    private User user;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ReactionType reactionType;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ReactionType getReactionType() {
        return reactionType;
    }

    public void setReactionType(ReactionType reactionType) {
        this.reactionType = reactionType;
    }
}
