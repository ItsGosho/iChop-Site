package ichop.threads.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.MongoEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Document("threads")
public class Thread extends MongoEntity {

    @NotNull
    private String creatorUsername;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime createdOn = LocalDateTime.now();

    private Integer views = 0;

}
