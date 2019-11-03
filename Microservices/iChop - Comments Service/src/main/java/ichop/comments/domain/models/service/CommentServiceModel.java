package ichop.comments.domain.models.service;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.BaseServiceModel;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class CommentServiceModel extends BaseServiceModel {

    private String creatorUsername;
    private String content;
    private LocalDateTime createdOn = LocalDateTime.now();
}
