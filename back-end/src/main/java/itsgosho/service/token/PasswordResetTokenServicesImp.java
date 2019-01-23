package itsgosho.service.token;

import itsgosho.domain.entities.tokens.PasswordResetToken;
import itsgosho.domain.entities.users.User;
import itsgosho.exceptions.token.TokenNotFoundException;
import itsgosho.exceptions.user.UserCannotBeNullException;
import itsgosho.repository.token.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetTokenServicesImp implements PasswordResetTokenServices {

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    public PasswordResetTokenServicesImp(PasswordResetTokenRepository passwordResetTokenRepository) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @Override
    public boolean isValid(String token) {
        PasswordResetToken passwordResetToken = this.getTokenByToken(token);
        if(passwordResetToken==null){
            return false;
        }

        if(passwordResetToken.getExpiryDate().compareTo(LocalDateTime.now())<0){
            return false;
        }

        return true;
    }

    @Override
    public PasswordResetToken createToken(User user){

        if(user==null){
            throw new UserCannotBeNullException();
        }

        this.deleteOldestToken(user);

        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiryDate(LocalDateTime.now().plusSeconds(PasswordResetToken.getTokenExpiration()));
        passwordResetToken.setToken(token);
        this.passwordResetTokenRepository.save(passwordResetToken);

        return passwordResetToken;
    }

    @Override
    public PasswordResetToken getTokenByUser(User user) {
        return this.passwordResetTokenRepository.findByUser(user);
    }

    @Override
    public PasswordResetToken getTokenByToken(String token) {
        return this.passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void deleteOldestToken(User user){
        PasswordResetToken passwordResetToken = this.getTokenByUser(user);
        if(passwordResetToken!=null){
            this.passwordResetTokenRepository.delete(passwordResetToken);
        }
    }
}
