package ichop.threads.common.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
public abstract class BaseEntity {

    @Id
    private String id;

}
