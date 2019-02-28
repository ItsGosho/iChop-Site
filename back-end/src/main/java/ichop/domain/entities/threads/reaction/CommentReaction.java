package ichop.domain.entities.threads.reaction;

import ichop.domain.entities.threads.Comment;
import ichop.domain.entities.threads.Thread;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment_reactions")
public class CommentReaction extends ReactionBase {

    @ManyToOne(optional = false)
    private Comment comment;


    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
