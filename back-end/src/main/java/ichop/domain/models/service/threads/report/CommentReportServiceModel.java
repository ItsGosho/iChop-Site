package ichop.domain.models.service.threads.report;

import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

import java.time.LocalDateTime;

public class CommentReportServiceModel {

    private String id;
    private UserServiceModel user;
    private LocalDateTime reportDate;
    private String reason;
    private CommentServiceModel comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public CommentServiceModel getComment() {
        return comment;
    }

    public void setComment(CommentServiceModel comment) {
        this.comment = comment;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }
}
