package ichop.core.areas.reaction.domain.entities;

import ichop.core.areas.comment.domain.entities.Comment;
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

    @ManyToOne(targetEntity = Comment.class)
    private Comment comment;

}
