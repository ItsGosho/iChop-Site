package ichop.comments.domain.models.service;

import ichop.comments.domain.entities.Comment;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadCommentServiceModel extends CommentServiceModel {

    private String threadId;

}

