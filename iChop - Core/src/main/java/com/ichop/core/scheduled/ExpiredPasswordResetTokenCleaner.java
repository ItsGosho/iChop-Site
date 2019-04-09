package com.ichop.core.scheduled;

import com.ichop.core.areas.token.domain.models.service.PasswordResetTokenServiceModel;
import com.ichop.core.areas.token.services.PasswordResetTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpiredPasswordResetTokenCleaner {

    private final PasswordResetTokenServices passwordResetTokenServices;

    @Autowired
    public ExpiredPasswordResetTokenCleaner(PasswordResetTokenServices passwordResetTokenServices) {
        this.passwordResetTokenServices = passwordResetTokenServices;
    }

    /*
    *
    * Runs every 12 hours
    *
    * */
    @Scheduled(cron = "0 0 */12 ? * *")
    public void clearExpiredTokens(){
        List<PasswordResetTokenServiceModel> result = this.passwordResetTokenServices.findAllExpired();

        for (PasswordResetTokenServiceModel passwordResetTokenServiceModel : result) {
            this.passwordResetTokenServices.deleteTokenById(passwordResetTokenServiceModel.getId());
        }
    }
}
