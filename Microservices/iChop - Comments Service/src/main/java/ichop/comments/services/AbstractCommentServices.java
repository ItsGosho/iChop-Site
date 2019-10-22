package ichop.comments.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.comments.common.service.AbstractBaseService;
import ichop.comments.domain.entities.Comment;
import ichop.comments.domain.models.service.CommentServiceModel;
import ichop.comments.repositories.CommentRepository;

public abstract class AbstractCommentServices
        <E extends Comment, S extends CommentServiceModel, R extends CommentRepository<E>>
        extends AbstractBaseService<E, S, R>
        implements CommentServices<S> {

    public AbstractCommentServices(ObjectMapper objectMapper, R repository) {
        super(objectMapper, repository);
    }


    @Override
    public Long getTotalCreatorComments(String creatorId) {
        return super.repository.getTotalCreatorComments(creatorId);
    }
}
