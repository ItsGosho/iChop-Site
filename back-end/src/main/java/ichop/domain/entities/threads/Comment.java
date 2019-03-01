package ichop.domain.entities.threads;

import ichop.domain.entities.BaseEntity;
import ichop.domain.entities.threads.reaction.CommentReaction;
import ichop.domain.entities.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


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

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public List<CommentReaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<CommentReaction> reactions) {
        this.reactions = reactions;
    }
}
