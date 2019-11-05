package ichop.core.areas.report.requesters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommentReportRequesterImp implements CommentReportRequester {

    private final JmsHelper jmsHelper;
    private final ObjectMapper objectMapper;

    private final String createDestination;
    private final String deleteByIdDestination;
    private final String findPageableDestination;

    @Autowired
    public CommentReportRequesterImp(JmsHelper jmsHelper,
                                     ObjectMapper objectMapper,
                                     @Value("${artemis.queue.reports.comment.create}") String createDestination,
                                     @Value("${artemis.queue.reports.comment.delete.by.id}") String deleteByIdDestination,
                                     @Value("${artemis.queue.reports.comment.find.pageable}") String findPageableDestination) {

        this.jmsHelper = jmsHelper;
        this.objectMapper = objectMapper;

        this.createDestination = createDestination;
        this.deleteByIdDestination = deleteByIdDestination;
        this.findPageableDestination = findPageableDestination;
    }

}
