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
public abstract class Token extends BaseEntity {

    @Column(nullable = false)
    private String token;

    @ManyToOne(optional = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime expiryDate;
}
