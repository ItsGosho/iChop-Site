package ichop.domain.models.view.comment;

public class CommentReadThreadViewModel {

    private String id;
    private String content;
    private String creatorUsername;
    private Integer creatorTotalComments;

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

    public Integer getCreatorTotalComments() {
        return creatorTotalComments;
    }

    public void setCreatorTotalComments(Integer creatorTotalComments) {
        this.creatorTotalComments = creatorTotalComments;
    }
}
