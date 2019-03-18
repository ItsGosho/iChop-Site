package ichop;

import ichop.service.user.UserInformationServices;
import ichop.service.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CLR implements CommandLineRunner {

    private final UserServices userServices;
    private final UserInformationServices userInformationServices;

    @Autowired
    public CLR(UserServices userServices, UserInformationServices userInformationServices) {
        this.userServices = userServices;
        this.userInformationServices = userInformationServices;
    }

    @Override
    public void run(String... args) throws Exception {

        LocalDate localDate = LocalDate.parse("2015-07-15");

        System.out.println();
    }
}
