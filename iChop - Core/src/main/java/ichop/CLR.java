package ichop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.util.HashMap;

@Component
public class CLR implements CommandLineRunner {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public CLR(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        HashMap<String, Object> messages = new HashMap<>();
        messages.put("userUsername", "itsgosho");
        messages.put("avatarAsBinary64", "iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAIAAAD/gAIDAAACs0lEQVR4Ae3csWpTARiG4cQcW9tiXSq2gq2UWoU4OTh0chDcvAQX70S8DfEmnCUUwVXRgItFF60KQayhICR6kMI3Nd//QSZ9O/3lvD0nffJnOCGku//iWafwc2F11VZfvn6zTRssLy/ZrNs9Y5s2GI/HNhuNvtumDdbXL9qs9JjsWf6TAKzgiQYLrEAgSNkssAKBIGWzwAoEgpTNAisQCFI2C6xAIEibW71XlXxluuCzNZ/MufC35J1O7VGNjz/bx8bL0BIpAEsWdgLLEikASxZ2AssSKQBLFnYCyxIpAEsWdgLLEikASxZ2AssSKWg0zpzeNHszjwcHt48GQT0zPTh/Z+bx4OB2Z2BrNssSKQBLFnYCyxIpAEsWdgLLEikASxZ2AssSKQBLFnYCyxIpAEsWdgLLEikASxZ2qt5I39i9bc+1teY/0dye5NP+I3uq8fEv27TBvb27Nnt/OLJNG0xfD2zGZlkiBWDJwk5gWSIFYMnCTmBZIgVgycJOYFkiBWDJwk5gWSIFYMnCTmBZIgXdny8f67fTp5WlwgdwT//z9Ejx3rDp+Sd7caF0/1u5or9Y+n/+wz1YwZMLFliBQJCyWWAFAkHKZoEVCAQpmwVWIBCkbBZYgUCQNucWz1byyfS3zcY7D2wz32BSOd27p5Wq8k4BL8OK5EkDFliBQJCyWWAFAkHKZoEVCAQpmwVWIBCkbBZYgUCQNs9HO5V8Y+OyzTZt8TfoDZ/UQl9N+g9tdHjpvm3a4MeR/1ZhXoYVyZMGLLACgSBls8AKBIKUzQIrEAhSNgusQCBI2SywAoEgZbMCrGb32vVK/uHjgc02r1y1TRvM8XuctgrXGw7fFqpOv3/TZmyWJVIAlizsBJYlUgCWLOwEliVSAJYs7ASWJVIAlizsBJYlUgCWLOwEliVS8Ad45EJSKeASrQAAAABJRU5ErkJggg==");

        Session session = this.jmsTemplate.getConnectionFactory().createConnection().createSession(false,Session.AUTO_ACKNOWLEDGE);
        ObjectMessage objectMessage = session.createObjectMessage();
        objectMessage.setObject(messages);

        this.jmsTemplate.convertAndSend("set-user-avatar", objectMessage);
    }
}
