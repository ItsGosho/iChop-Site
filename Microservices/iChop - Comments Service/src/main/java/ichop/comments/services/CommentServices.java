package ichop.comments.services;

import ichop.comments.common.service.BaseService;
import ichop.comments.domain.entities.Comment;
import ichop.comments.domain.models.service.CommentServiceModel;
import ichop.comments.repositories.CommentRepository;

public interface CommentServices<S extends CommentServiceModel> extends BaseService<S> {

    Long getTotalCreatorComments(String creatorId);

}
