package ichop.comments.services;

import ichop.comments.common.service.BaseService;
import ichop.comments.domain.models.service.CommentServiceModel;

public interface CommentServices<S extends CommentServiceModel> extends BaseService<S> {

    Long getTotalCreatorComments(String creatorUsername);

}
