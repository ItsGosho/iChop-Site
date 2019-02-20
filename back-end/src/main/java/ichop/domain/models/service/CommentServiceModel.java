package ichop.domain.models.service;

import java.time.LocalDateTime;

public class CommentServiceModel {

    private String id;
    public ThreadServiceModel thread;
    public String content;
    public UserServiceModel creator;
    public LocalDateTime createdOn;


    public ThreadServiceModel getThread() {
        return thread;
    }

    public void setThread(ThreadServiceModel thread) {
        this.thread = thread;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserServiceModel getCreator() {
        return creator;
    }

    public void setCreator(UserServiceModel creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
