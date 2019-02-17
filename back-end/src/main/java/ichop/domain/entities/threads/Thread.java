package ichop.domain.entities.threads;

import ichop.domain.entities.BaseEntity;
import ichop.domain.entities.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "threads")
public class Thread extends BaseEntity {

    private String title;

    @Lob
    private String content;

    @ManyToOne
    private User creator;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @OneToMany
    private List<Comment> comments;

    private Integer views;

    @Column(columnDefinition = "int default 0")
    private Integer rowsForNewsPage;

    @OneToMany
    private List<React> reacts;

    public Thread(){
        this.setComments(new LinkedList<>());
        this.setReacts(new LinkedList<>());
        this.setViews(0);
        this.rowsForNewsPage = 0;
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

    public List<React> getReacts() {
        return reacts;
    }

    public void setReacts(List<React> reacts) {
        this.reacts = reacts;
    }

    public Integer getRowsForNewsPage() {
        return rowsForNewsPage;
    }

    public void setRowsForNewsPage(Integer rowsForNewsPage) {
        this.rowsForNewsPage = rowsForNewsPage;
    }
}
