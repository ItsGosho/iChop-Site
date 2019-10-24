package ichop.core.areas.comment.domain.entities;

import ichop.core.areas.reaction.domain.entities.CommentReaction;
import ichop.core.base.BaseEntity;
import ichop.core.areas.report.domain.entities.CommentReport;
import ichop.core.areas.thread.domain.entities.Thread;
import ichop.core.areas.user.domain.entities.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity(name = "Comment")
@Table(name = "comments")
public class Comment extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Thread.class)
    public Thread thread;

    @Column(name = "content",nullable = false)
    public String content;

    @ManyToOne(targetEntity = User.class)
    public User creator;

    @Column(name="created_on" ,nullable = false,updatable = false)
    public LocalDateTime createdOn;

    @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL, orphanRemoval = true,targetEntity = CommentReaction.class)
    private List<CommentReaction> reactions;

    @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL, orphanRemoval = true,targetEntity = CommentReport.class)
    private List<CommentReport> reports;

    public Comment(){
        this.setReactions(new LinkedList<>());
        this.setReports(new LinkedList<>());
    }
}
