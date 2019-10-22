package ichop.comments.domain.models.service;

import ichop.comments.common.domain.BaseEntity;
import ichop.comments.common.domain.BaseServiceModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class CommentServiceModel extends BaseServiceModel {

    private String creatorId;
    private String content;
    private LocalDateTime createdOn = LocalDateTime.now();
}
