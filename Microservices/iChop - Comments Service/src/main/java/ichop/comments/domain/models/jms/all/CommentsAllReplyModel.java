package ichop.comments.domain.models.jms.all;

import ichop.comments.common.domain.BaseReplyModel;
import ichop.comments.domain.models.service.CommentServiceModel;
import ichop.comments.domain.models.service.ThreadCommentServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CommentsAllReplyModel<S extends CommentServiceModel> extends BaseReplyModel {

    private List<S> comments;

    public CommentsAllReplyModel(List<S> comments) {
        this.comments = comments;
    }

}
