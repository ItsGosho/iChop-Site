package ichop.domain.entities.threads;

import ichop.domain.entities.BaseEntity;
import ichop.domain.entities.users.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    @ManyToOne(optional = false)
    public Thread thread;

    @Column(nullable = false)
    public String content;

    @ManyToOne(optional = false)
    public User creator;

    @Column(nullable = false)
    public LocalDateTime createdOn;

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
}
