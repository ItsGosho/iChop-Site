package ichop.core.areas.report.domain.models.service;

import ichop.core.areas.post.domain.models.service.PostServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostReportServiceModel extends BaseReportServiceModel {

    private PostServiceModel post;

}
