package ichop.threads.domain.models.service;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.BaseServiceModel;

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
