package ichop.service.report.crud;

import ichop.domain.entities.post.PostReport;
import ichop.domain.entities.report.CommentReport;
import ichop.domain.entities.report.ThreadReport;
import ichop.domain.models.service.report.CommentReportServiceModel;
import ichop.domain.models.service.report.ThreadReportServiceModel;
import ichop.domain.models.service.report.PostReportServiceModel;
import ichop.repository.report.CommentReportRepository;
import ichop.repository.report.PostReportRepository;
import ichop.repository.report.ThreadReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportCrudServicesImp implements ReportCrudServices {


    private final ThreadReportRepository threadReportRepository;
    private final CommentReportRepository commentReportRepository;
    private final PostReportRepository postReportRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReportCrudServicesImp(ThreadReportRepository threadReportRepository, CommentReportRepository commentReportRepository, PostReportRepository postReportRepository, ModelMapper modelMapper) {
        this.threadReportRepository = threadReportRepository;
        this.commentReportRepository = commentReportRepository;
        this.postReportRepository = postReportRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ThreadReportServiceModel save(ThreadReportServiceModel threadReport) {

        ThreadReport entityThreadReport = this.modelMapper.map(threadReport,ThreadReport.class);
        this.threadReportRepository.save(entityThreadReport);

        return this.modelMapper.map(entityThreadReport,ThreadReportServiceModel.class);
    }

    @Override
    public CommentReportServiceModel save(CommentReportServiceModel commentReport) {

        CommentReport entityCommentReport = this.modelMapper.map(commentReport,CommentReport.class);
        this.commentReportRepository.save(entityCommentReport);

        return this.modelMapper.map(entityCommentReport,CommentReportServiceModel.class);
    }

    @Override
    public PostReportServiceModel save(PostReportServiceModel postReport) {
        PostReport entityPostReport = this.modelMapper.map(postReport,PostReport.class);
        this.postReportRepository.save(entityPostReport);

        return this.modelMapper.map(entityPostReport,PostReportServiceModel.class);
    }
}
