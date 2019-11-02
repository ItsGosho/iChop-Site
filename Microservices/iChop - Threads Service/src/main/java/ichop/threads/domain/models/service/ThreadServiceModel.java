package ichop.threads.domain.models.service;

import ichop.threads.common.domain.BaseServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ThreadServiceModel extends BaseServiceModel {

    private String creatorUsername;
    private String title;
    private String content;
    private LocalDateTime createdOn = LocalDateTime.now();
    private Integer views = 0;

}
