package ichop.service.report;

import ichop.domain.models.service.post.PostServiceModel;
import ichop.domain.models.service.report.PostReportServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface PostReportServices {


    PostReportServiceModel createReport(PostServiceModel post, UserServiceModel user, String reason);

}
