package ichop.domain.models.view.comment;

import java.time.LocalDateTime;

public class CommentReadThreadViewModel {

    private String id;
    private String content;
    private String creatorUsername;
    private Integer creatorTotalComments;
    private LocalDateTime createdOn;
    private Integer totalLikes;
    private Integer totalDislikes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getCreatorTotalComments() {
        return creatorTotalComments;
    }

    public void setCreatorTotalComments(Integer creatorTotalComments) {
        this.creatorTotalComments = creatorTotalComments;
    }

    public Integer getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(Integer totalLikes) {
        this.totalLikes = totalLikes;
    }

    public Integer getTotalDislikes() {
        return totalDislikes;
    }

    public void setTotalDislikes(Integer totalDislikes) {
        this.totalDislikes = totalDislikes;
    }
}
