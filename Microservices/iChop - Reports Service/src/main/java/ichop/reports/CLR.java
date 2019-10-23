package ichop.reports;

import ichop.reports.common.validation.ValidationHelper;
import ichop.reports.domain.enums.Type;
import ichop.reports.domain.models.jms.create.request.CommentReportCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CLR implements CommandLineRunner {

    private final ValidationHelper validationHelper;

    @Autowired
    public CLR(ValidationHelper validationHelper) {
        this.validationHelper = validationHelper;
    }

    @Override
    public void run(String... args) throws Exception {
        CommentReportCreateRequest commentReportCreateRequest = new CommentReportCreateRequest();
        commentReportCreateRequest.setCommentId("joreto");
        commentReportCreateRequest.setUserId("joreto");
        commentReportCreateRequest.setType(Type.THREAD_COMMENT);
        commentReportCreateRequest.setReason("Resons ?!");

        if (!this.validationHelper.isValid(commentReportCreateRequest)) {
            String message = this.validationHelper.getValidationError(commentReportCreateRequest);
            System.out.println();
        }
    }

}
