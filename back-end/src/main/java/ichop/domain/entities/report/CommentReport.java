package ichop.domain.entities.report;

import ichop.domain.entities.comment.Comment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "comments_reports")
public class CommentReport extends BaseReport {

    @ManyToOne(optional = false)
    private Comment comment;

}
