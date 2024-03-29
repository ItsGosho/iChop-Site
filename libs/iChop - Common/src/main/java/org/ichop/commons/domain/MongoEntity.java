package org.ichop.commons.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public abstract class MongoEntity {

    @Id
    private String id;

}
