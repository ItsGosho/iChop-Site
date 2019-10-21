package ichop.comments.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.comments.domain.entities.ThreadComment;
import ichop.comments.domain.models.service.ThreadCommentServiceModel;
import ichop.comments.repositories.ThreadCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadCommentServicesImp extends AbstractCommentServices<ThreadComment, ThreadCommentServiceModel, ThreadCommentRepository> implements ThreadCommentServices {

    @Autowired
    public ThreadCommentServicesImp(ObjectMapper objectMapper, ThreadCommentRepository repository) {
        super(objectMapper, repository);
    }


    @Override
    public boolean existsByThreadId(String threadId) {
        return super.repository.existsByThreadId(threadId);
    }

    @Override
    public List<ThreadCommentServiceModel> findAllByThreadId(String threadId) {
        List<ThreadComment> comments = super.repository.findByThreadId(threadId);

        return super.mapToList(comments,ThreadCommentServiceModel.class);
    }
}
