package ichop.tokens.scheduled;

import ichop.tokens.domain.models.service.PasswordTokenServiceModel;
import ichop.tokens.services.PasswordTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledJobs {

    private final PasswordTokenServices passwordResetTokenServices;

    @Autowired
    public ScheduledJobs(PasswordTokenServices passwordResetTokenServices) {
        this.passwordResetTokenServices = passwordResetTokenServices;
    }

    //12h
    @Scheduled(cron = "0 0 */12 ? * *")
    public void clearExpiredTokens() {
        List<PasswordTokenServiceModel> result = this.passwordResetTokenServices.findExpired();

        for (PasswordTokenServiceModel token : result) {
            this.passwordResetTokenServices.deleteById(token.getId());
        }
    }

}
