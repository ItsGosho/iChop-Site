package ichop.comments.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.MongoEntity;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class Comment extends MongoEntity {

    @NotNull
    private String creatorUsername;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime createdOn = LocalDateTime.now();
}
