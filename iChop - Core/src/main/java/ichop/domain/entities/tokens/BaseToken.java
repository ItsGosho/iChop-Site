package ichop.domain.entities.tokens;

import ichop.domain.entities.BaseEntity;
import ichop.domain.entities.users.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseToken extends BaseEntity {

    @Column(name = "token",nullable = false,updatable = false)
    private String token;

    @ManyToOne(optional = false,targetEntity = User.class)
    private User user;

    @Column(name = "expiry_date",nullable = false,updatable = false)
    private LocalDateTime expiryDate;
}
