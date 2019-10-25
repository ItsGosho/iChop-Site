package ichop.emails.helpers;


import ichop.emails.domain.models.JavaMailModel;

import javax.mail.MessagingException;

public interface JavaMailHelper {

    void send(JavaMailModel javaMailModel) throws MessagingException;
}
