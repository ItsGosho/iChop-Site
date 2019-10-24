package ichop.core.areas.report.helpers;

import ichop.core.areas.report.domain.models.view.PostAllReportsViewModel;
import ichop.core.areas.report.services.PostReportServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PostAllReportsViewHelper {

    private final PostReportServices postReportServices;
    private final ModelMapper modelMapper;

    @Autowired
    public PostAllReportsViewHelper(PostReportServices postReportServices, ModelMapper modelMapper) {
        this.postReportServices = postReportServices;
        this.modelMapper = modelMapper;
    }


    public Page<PostAllReportsViewModel> create(Pageable pageable) {

        Page<PostAllReportsViewModel> result = this.postReportServices.findAll(pageable).map(x -> {

            PostAllReportsViewModel postAllViewModel = this.modelMapper.map(x, PostAllReportsViewModel.class);
            postAllViewModel.setCreatorUsername(x.getUser().getUsername());
            postAllViewModel.setReportId(x.getId());
            postAllViewModel.setPostId(x.getPost().getId());
            postAllViewModel.setPostContent(x.getPost().getContent());

            return postAllViewModel;
        });

        return result;
    }

}
