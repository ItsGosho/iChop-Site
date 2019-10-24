package ichop.core.areas.report.helpers;

import ichop.core.areas.report.domain.models.view.ThreadAllReportsViewModel;
import ichop.core.areas.report.services.ThreadReportServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ThreadAllReportsViewHelper {

    private final ThreadReportServices threadReportServices;
    private final ModelMapper modelMapper;

    @Autowired
    public ThreadAllReportsViewHelper(ThreadReportServices threadReportServices, ModelMapper modelMapper) {
        this.threadReportServices = threadReportServices;
        this.modelMapper = modelMapper;
    }

    public Page<ThreadAllReportsViewModel> create(Pageable pageable) {

        Page<ThreadAllReportsViewModel> result = this.threadReportServices.findAll(pageable).map(x -> {

            ThreadAllReportsViewModel threadAllViewModel = this.modelMapper.map(x, ThreadAllReportsViewModel.class);
            threadAllViewModel.setCreatorUsername(x.getUser().getUsername());
            threadAllViewModel.setReportId(x.getId());
            threadAllViewModel.setThreadId(x.getThread().getId());

            return threadAllViewModel;
        });

        return result;
    }

}
