package ichop.domain.entities.tokens;

import ichop.domain.entities.BaseEntity;
import ichop.domain.entities.users.User;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Token extends BaseEntity {

    @Column(nullable = false)
    private String token;

    @ManyToOne(optional = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
