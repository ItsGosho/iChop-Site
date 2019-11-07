package ichop.comments.domain.entities;

import ichop.comments.annotations.CommentType;
import ichop.comments.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Document("thread_comments")
@CommentType(type = Type.THREAD)
public class ThreadComment extends Comment {

    @NotNull
    private String threadId;
}

