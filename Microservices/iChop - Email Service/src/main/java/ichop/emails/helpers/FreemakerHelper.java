package ichop.emails.helpers;

import freemarker.template.TemplateException;

import java.io.IOException;

public interface FreemakerHelper {


    String proceed(String templatePath, Object object) throws Exception;

}
