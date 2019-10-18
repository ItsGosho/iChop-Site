package ichop.threads.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
public abstract class BaseEntity {

    @Indexed(unique = true)
    private String id;

}
