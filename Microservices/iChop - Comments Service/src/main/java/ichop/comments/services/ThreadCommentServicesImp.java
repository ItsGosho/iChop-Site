package ichop.comments.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.comments.domain.entities.ThreadComment;
import ichop.comments.domain.models.service.ThreadCommentServiceModel;
import ichop.comments.repositories.ThreadCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreadCommentServicesImp extends AbstractCommentServices<ThreadComment, ThreadCommentServiceModel, ThreadCommentRepository> implements ThreadCommentServices {

    @Autowired
    public ThreadCommentServicesImp(ObjectMapper objectMapper, ThreadCommentRepository repository) {
        super(objectMapper, repository);
    }



}
