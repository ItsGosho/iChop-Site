package ichop.emails.helpers;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Component
@SuppressWarnings("all")
public class FreemakerHelperImp implements FreemakerHelper {

    private final Configuration configuration;


    public FreemakerHelperImp(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public String proceed(String templatePath, Object object) throws Exception {
        Writer out = new StringBuilderWriter();
        Template template = this.configuration.getTemplate(templatePath);

        String key = StringUtils.uncapitalize(object.getClass().getSimpleName());
        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put(key, object);

        template.process(wrapper, out);
        return out.toString();
    }

    @Override
    public String proceedPasswordReset() throws Exception {
        return null;
    }

}
