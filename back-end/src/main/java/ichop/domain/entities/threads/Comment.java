package ichop.domain.entities.threads;

import ichop.domain.entities.BaseEntity;
import ichop.domain.entities.threads.reaction.CommentReaction;
import ichop.domain.entities.users.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    public Thread thread;

    @Column(nullable = false)
    public String content;

    @ManyToOne(optional = false)
    public User creator;

    @Column(nullable = false)
    public LocalDateTime createdOn;

    @OneToMany
    @JoinTable(name = "comment_reactions",joinColumns = @JoinColumn(name = "comment_id"),inverseJoinColumns = @JoinColumn(name = "id"))
    private List<CommentReaction> reactions;

    public Comment(){
        this.setReactions(new LinkedList<>());
    }
}
