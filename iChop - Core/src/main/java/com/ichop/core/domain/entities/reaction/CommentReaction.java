package com.ichop.core.domain.entities.reaction;

import com.ichop.core.domain.entities.comment.Comment;
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

    @ManyToOne(optional = false,targetEntity = Comment.class)
    private Comment comment;

}
