package itsgosho.components.email;

public interface EmailServices {
    void sendSimpleMessage(String to, String subject, String text);
}
