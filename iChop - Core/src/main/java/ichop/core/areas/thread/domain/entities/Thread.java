package ichop.core.areas.thread.domain.entities;

import ichop.core.base.BaseEntity;
import ichop.core.areas.comment.domain.entities.Comment;
import ichop.core.areas.reaction.domain.entities.ThreadReaction;
import ichop.core.areas.user.domain.entities.User;
import ichop.core.areas.report.domain.entities.ThreadReport;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity(name = "Thread")
@Table(name = "threads")
public class Thread extends BaseEntity {

    @Column(name = "title",nullable = false)
    private String title;

    @Lob
    @Column(name = "content",nullable = false)
    private String content;

    @ManyToOne(targetEntity = User.class)
    private User creator;

    @Column(name = "created_on", nullable = false,updatable = false)
    private LocalDateTime createdOn;

    @OneToMany(mappedBy = "thread",orphanRemoval = true,targetEntity = Comment.class)
    private List<Comment> comments;

    @Column(name = "views",nullable = false)
    private Integer views;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true,targetEntity = ThreadReaction.class)
    private List<ThreadReaction> reactions;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true,targetEntity = ThreadReport.class)
    private List<ThreadReport> reports;

    public Thread() {
        this.setComments(new LinkedList<>());
        this.setReactions(new LinkedList<>());
        this.setReports(new LinkedList<>());
        this.setViews(0);
    }

}
