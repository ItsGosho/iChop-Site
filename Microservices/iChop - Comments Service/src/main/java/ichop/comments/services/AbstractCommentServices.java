package ichop.comments.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.comments.domain.entities.Comment;
import ichop.comments.domain.models.service.CommentServiceModel;
import ichop.comments.repositories.CommentRepository;
import org.ichop.commons.service.BaseMongoService;

public abstract class AbstractCommentServices
        <E extends Comment, S extends CommentServiceModel, R extends CommentRepository<E>>
        extends BaseMongoService<E, S, R>
        implements CommentServices<S> {

    public AbstractCommentServices(ObjectMapper objectMapper, R repository) {
        super(objectMapper, repository);
    }


    @Override
    public Long getTotalCreatorComments(String creatorUsername) {
        return super.repository.getTotalCreatorComments(creatorUsername);
    }

}
