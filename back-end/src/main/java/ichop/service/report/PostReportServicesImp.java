package ichop.service.report;

import ichop.domain.entities.post.PostReport;
import ichop.domain.models.service.post.PostServiceModel;
import ichop.domain.models.service.report.PostReportServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.post.PostNotFoundException;
import ichop.repository.report.PostReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostReportServicesImp extends BaseReportServices<PostReport, PostReportRepository> implements PostReportServices {


    @Autowired
    public PostReportServicesImp(ModelMapper modelMapper, PostReportRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public PostReportServiceModel createReport(PostServiceModel post, UserServiceModel user, String reason) {
        if(post == null){
            throw new PostNotFoundException();
        }

        PostReportServiceModel postReport = new PostReportServiceModel();
        postReport.setPost(post);
        postReport.setUser(user);
        postReport.setReason(reason);
        postReport.setReportDate(LocalDateTime.now());

        PostReportServiceModel result = super.save(postReport,PostReportServiceModel.class);

        return result;
    }
}
