package ichop.domain.entities.threads.reaction;

import ichop.domain.entities.threads.Comment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "CommentReaction")
@Table(name = "comments_reactions")
public class CommentReaction extends BaseReaction {

    @ManyToOne(optional = false)
    private Comment comment;

}
