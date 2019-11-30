package ichop.users;

import ichop.users.domain.models.jms.retrieve.UserFindByUsernameRequest;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CLR implements CommandLineRunner {

    private final JmsHelper jmsHelper;

    @Autowired
    public CLR(JmsHelper jmsHelper) {
        this.jmsHelper = jmsHelper;
    }

    @Override
    public void run(String... args) throws Exception {

        UserFindByUsernameRequest request = new UserFindByUsernameRequest();
        request.setUsername("joreto");

        this.jmsHelper.send("proba",request);
    }
}
