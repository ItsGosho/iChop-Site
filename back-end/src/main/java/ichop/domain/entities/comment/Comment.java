package ichop.domain.entities.comment;

import ichop.domain.entities.BaseEntity;
import ichop.domain.entities.reaction.CommentReaction;
import ichop.domain.entities.report.CommentReport;
import ichop.domain.entities.threads.Thread;
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
@Table(name = "comments")
public class Comment extends BaseEntity {

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    public Thread thread;

    @Column(nullable = false)
    public String content;

    @ManyToOne(optional = false)
    public User creator;

    @Column(nullable = false)
    public LocalDateTime createdOn;

    @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentReaction> reactions;

    @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentReport> reports;

    public Comment(){
        this.setReactions(new LinkedList<>());
        this.setReports(new LinkedList<>());
    }
}
