package ichop.emails.helpers;

public interface FreemakerHelper {


    String proceed(String templatePath, Object object) throws Exception;
    String proceedPasswordReset() throws Exception;

}
