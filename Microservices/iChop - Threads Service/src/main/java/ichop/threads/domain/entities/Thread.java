package ichop.threads.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Document("threads")
public class Thread extends BaseEntity {

    @NotNull
    private String userId;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime createdOn = LocalDateTime.now();

    private Integer views = 0;

}
