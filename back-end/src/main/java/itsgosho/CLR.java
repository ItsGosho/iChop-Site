package itsgosho;

import itsgosho.service.token.PasswordResetTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CLR implements CommandLineRunner {

    private final PasswordResetTokenServices passwordResetTokenServices;

    @Autowired
    public CLR(PasswordResetTokenServices passwordResetTokenServices) {
        this.passwordResetTokenServices = passwordResetTokenServices;
    }

    @Override
    public void run(String... args) throws Exception {
        //System.out.println(this.passwordResetTokenServices.isValid("9504da60-a94b-4ae1-958e-a1e080b7ef1d"));
    }
}
