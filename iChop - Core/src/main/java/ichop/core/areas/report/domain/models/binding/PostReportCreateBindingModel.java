package ichop.core.areas.report.domain.models.binding;

import ichop.core.areas.post.domain.models.service.PostServiceModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostReportCreateBindingModel extends BaseReportCreateBindingModel {

    @NotNull
    private PostServiceModel post;

}
