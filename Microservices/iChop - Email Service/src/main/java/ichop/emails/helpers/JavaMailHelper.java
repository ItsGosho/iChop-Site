package ichop.emails.helpers;


import javax.mail.MessagingException;

public interface JavaMailHelper {

    void send(String to, String subject, String htmlContent) throws MessagingException;
}
