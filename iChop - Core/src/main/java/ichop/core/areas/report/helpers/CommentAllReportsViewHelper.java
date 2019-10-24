package ichop.core.areas.report.helpers;

import ichop.core.areas.report.domain.models.service.CommentReportServiceModel;
import ichop.core.areas.report.domain.models.view.CommentAllReportsViewModel;
import ichop.core.areas.report.services.CommentReportServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class CommentAllReportsViewHelper {

    private final CommentReportServices commentReportServices;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentAllReportsViewHelper(CommentReportServices commentReportServices, ModelMapper modelMapper) {
        this.commentReportServices = commentReportServices;
        this.modelMapper = modelMapper;
    }


    public Page<CommentAllReportsViewModel> create(Pageable pageable) {

        Page<CommentReportServiceModel> foundedReports = this.commentReportServices.findAll(pageable);

        Page<CommentAllReportsViewModel> result = foundedReports.map(x -> {

            CommentAllReportsViewModel commentAllViewModel = this.modelMapper.map(x, CommentAllReportsViewModel.class);
            commentAllViewModel.setCreatorUsername(x.getUser().getUsername());
            commentAllViewModel.setReportId(x.getId());
            commentAllViewModel.setCommentId(x.getComment().getId());
            commentAllViewModel.setCommentContent(x.getComment().getContent());

            return commentAllViewModel;
        });

        return result;
    }

}
