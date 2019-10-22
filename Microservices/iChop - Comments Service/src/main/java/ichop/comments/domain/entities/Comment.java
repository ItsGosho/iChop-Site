package ichop.comments.domain.entities;

import ichop.comments.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class Comment extends BaseEntity {

    @NotNull
    private String creatorId;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime createdOn = LocalDateTime.now();
}
