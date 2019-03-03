package ichop.service.threads.report.crud;

import ichop.domain.entities.threads.report.CommentReport;
import ichop.domain.entities.threads.report.ThreadReport;
import ichop.domain.models.service.threads.report.CommentReportServiceModel;
import ichop.domain.models.service.threads.report.ThreadReportServiceModel;
import ichop.repository.threads.report.CommentReportRepository;
import ichop.repository.threads.report.ThreadReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportCrudServicesImp implements ReportCrudServices {


    private final ThreadReportRepository threadReportRepository;
    private final CommentReportRepository commentReportRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReportCrudServicesImp(ThreadReportRepository threadReportRepository, CommentReportRepository commentReportRepository, ModelMapper modelMapper) {
        this.threadReportRepository = threadReportRepository;
        this.commentReportRepository = commentReportRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ThreadReportServiceModel save(ThreadReportServiceModel threadReportServiceModel) {

        ThreadReport threadReport = this.modelMapper.map(threadReportServiceModel,ThreadReport.class);
        this.threadReportRepository.save(threadReport);

        return this.modelMapper.map(threadReport,ThreadReportServiceModel.class);
    }

    @Override
    public CommentReportServiceModel save(CommentReportServiceModel commentReportServiceModel) {

        CommentReport commentReport = this.modelMapper.map(commentReportServiceModel,CommentReport.class);
        this.commentReportRepository.save(commentReport);

        return this.modelMapper.map(commentReport,CommentReportServiceModel.class);
    }
}
