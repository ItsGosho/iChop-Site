package ichop.domain.entities.threads;

import ichop.domain.entities.BaseEntity;
import ichop.domain.entities.comment.Comment;
import ichop.domain.entities.reaction.ThreadReaction;
import ichop.domain.entities.report.ThreadReport;
import ichop.domain.entities.users.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
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

    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;

    @OneToMany(mappedBy = "thread",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments;

    @Column(nullable = false)
    private Integer views;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ThreadReaction> reactions;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ThreadReport> reports;

    public Thread() {
        this.setComments(new LinkedList<>());
        this.setReactions(new LinkedList<>());
        this.setReports(new LinkedList<>());
        this.setViews(0);
    }

}
