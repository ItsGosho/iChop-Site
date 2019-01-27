package itsgosho.domain.models.view.thread;

import itsgosho.domain.entities.users.User;

import java.time.LocalDateTime;

public class ThreadHomepageViewModel {

    private String id;
    private String title;
    private LocalDateTime createdOn;
    private User creator;
    private Integer totalViews;
    private Integer totalReactions;
    private Integer totalComments;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Integer getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(Integer totalViews) {
        this.totalViews = totalViews;
    }

    public Integer getTotalReactions() {
        return totalReactions;
    }

    public void setTotalReactions(Integer totalReactions) {
        this.totalReactions = totalReactions;
    }

    public Integer getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(Integer totalComments) {
        this.totalComments = totalComments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
