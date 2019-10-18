package ichop.threads.domain.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("threads")
public class Thread extends BaseEntity {

    private String title;

}
