package itsgosho.domain.entities.tokens;

import itsgosho.domain.entities.BaseEntity;
import itsgosho.domain.entities.users.User;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Token extends BaseEntity {

    @Column(unique = true)
    private String token;

    @OneToOne(optional = false)
    private User user;

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
