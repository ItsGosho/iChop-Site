package ichop.domain.models.service.threads.report;

import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.threads.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

import java.time.LocalDateTime;

public class ThreadReportServiceModel {

    private String id;
    private UserServiceModel user;
    private LocalDateTime reportDate;
    private String reason;
    private ThreadServiceModel thread;

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

    public ThreadServiceModel getThread() {
        return thread;
    }

    public void setThread(ThreadServiceModel thread) {
        this.thread = thread;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }
}
