package ichop.domain.models.service;

import ichop.domain.entities.users.User;

import java.time.LocalDateTime;

public class PasswordResetTokenServiceModel {

    private String id;
    private String token;
    private UserServiceModel user;
    private LocalDateTime expiryDate;
    private Integer TOKEN_EXPIRATION_IN_SECONDS;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getTOKEN_EXPIRATION_IN_SECONDS() {
        return TOKEN_EXPIRATION_IN_SECONDS;
    }

    public void setTOKEN_EXPIRATION_IN_SECONDS(Integer TOKEN_EXPIRATION_IN_SECONDS) {
        this.TOKEN_EXPIRATION_IN_SECONDS = TOKEN_EXPIRATION_IN_SECONDS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
