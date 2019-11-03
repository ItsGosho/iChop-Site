package ichop.comments.services;

import ichop.comments.domain.models.service.CommentServiceModel;
import org.ichop.commons.service.BaseService;

public interface CommentServices<S extends CommentServiceModel> extends BaseService<S> {

    Long getTotalCreatorComments(String creatorUsername);

}
