package ichop.domain.entities.threads;

import ichop.domain.entities.BaseEntity;
import ichop.domain.entities.threads.reaction.ThreadReaction;
import ichop.domain.entities.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "threads")
public class Thread extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne(optional = false)
    private User creator;

    @Column(name = "created_on",nullable = false)
    private LocalDateTime createdOn;

    @OneToMany
    @JoinTable(name = "comments",joinColumns = @JoinColumn(name = "thread_id"),inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Comment> comments;

    @Column(nullable = false)
    private Integer views;

    @OneToMany(mappedBy = "thread")
    private List<ThreadReaction> reactions;

    public Thread(){
        this.setComments(new LinkedList<>());
        this.setReactions(new LinkedList<>());
        this.setViews(0);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public List<ThreadReaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<ThreadReaction> reactions) {
        this.reactions = reactions;
    }
}
