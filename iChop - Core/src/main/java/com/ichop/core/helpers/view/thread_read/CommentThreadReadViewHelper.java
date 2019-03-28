package com.ichop.core.helpers.view.thread_read;

import com.ichop.core.domain.entities.reaction.ReactionType;
import com.ichop.core.domain.models.view.thread_read.CommentCreatorThreadReadViewModel;
import com.ichop.core.domain.models.view.thread_read.CommentThreadReadViewModel;
import com.ichop.core.helpers.view.BaseViewCreator;
import com.ichop.core.service.thread.ThreadServices;
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


    public List<CommentThreadReadViewModel> create(String threadId){
        List<CommentThreadReadViewModel> result = new LinkedList<>();

        this.threadServices.findThreadById(threadId).getComments().forEach(x -> {
            CommentThreadReadViewModel commentThread = super.modelMapper.map(x,CommentThreadReadViewModel.class);

            CommentCreatorThreadReadViewModel commentCreator = this.commentCreatorThreadReadViewHelper.create(x.getId());
            long totalLikes = x.getReactions().stream().filter(z-> z.getReactionType().equals(ReactionType.LIKE)).count();
            long totalDislikes = x.getReactions().stream().filter(z-> z.getReactionType().equals(ReactionType.DISLIKE)).count();

            commentThread.setTotalLikes(totalLikes);
            commentThread.setTotalDislikes(totalDislikes);
            commentThread.setCommentCreator(commentCreator);

            result.add(commentThread);
        });

        return result;
    }

}
