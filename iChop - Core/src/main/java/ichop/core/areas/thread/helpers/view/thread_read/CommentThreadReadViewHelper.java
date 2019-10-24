package ichop.core.areas.thread.helpers.view.thread_read;

import ichop.core.areas.reaction.domain.entities.ReactionType;
import ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import ichop.core.areas.thread.domain.models.view.thread_read.CommentCreatorThreadReadViewModel;
import ichop.core.areas.thread.domain.models.view.thread_read.CommentThreadReadViewModel;
import ichop.core.areas.thread.services.ThreadServices;
import ichop.core.base.BaseViewCreator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class CommentThreadReadViewHelper extends BaseViewCreator {

    private final ThreadServices threadServices;
    private final CommentCreatorThreadReadViewHelper commentCreatorThreadReadViewHelper;

    public CommentThreadReadViewHelper(ModelMapper modelMapper, ThreadServices threadServices, CommentCreatorThreadReadViewHelper commentCreatorThreadReadViewHelper) {
        super(modelMapper);
        this.threadServices = threadServices;
        this.commentCreatorThreadReadViewHelper = commentCreatorThreadReadViewHelper;
    }


    public List<CommentThreadReadViewModel> create(String threadId) {
        List<CommentThreadReadViewModel> result = new LinkedList<>();
        ThreadServiceModel thread = this.threadServices.findById(threadId);

        thread.getComments().forEach(x -> {
            CommentThreadReadViewModel commentThread = super.modelMapper.map(x, CommentThreadReadViewModel.class);

            CommentCreatorThreadReadViewModel commentCreator = this.commentCreatorThreadReadViewHelper.create(x.getId());
            long totalLikes = x.getReactions().stream().filter(z -> z.getReactionType().equals(ReactionType.LIKE)).count();
            long totalDislikes = x.getReactions().stream().filter(z -> z.getReactionType().equals(ReactionType.DISLIKE)).count();

            commentThread.setTotalLikes(totalLikes);
            commentThread.setTotalDislikes(totalDislikes);
            commentThread.setCommentCreator(commentCreator);

            result.add(commentThread);
        });

        return result;
    }

}
