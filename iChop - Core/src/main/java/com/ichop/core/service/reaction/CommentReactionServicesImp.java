package com.ichop.core.service.reaction;

import com.ichop.core.domain.entities.comment.Comment;
import com.ichop.core.domain.entities.reaction.CommentReaction;
import com.ichop.core.domain.entities.reaction.ReactionType;
import com.ichop.core.domain.entities.users.User;
import com.ichop.core.domain.models.service.comment.CommentServiceModel;
import com.ichop.core.domain.models.service.reaction.CommentReactionServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.exceptions.thread.CommentNotFoundException;
import com.ichop.core.exceptions.user.UserAlreadyReacted;
import com.ichop.core.repository.reaction.CommentReactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentReactionServicesImp extends BaseReactionServices<CommentReaction, CommentReactionRepository> implements CommentReactionServices {

    @Autowired
    public CommentReactionServicesImp(ModelMapper modelMapper, CommentReactionRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public CommentReactionServiceModel createReaction(CommentServiceModel comment, UserServiceModel user, ReactionType reactionType) {
        if (comment == null) {
            throw new CommentNotFoundException();
        }

        if (this.isCommentLikedByUser(user, comment)) {
            throw new UserAlreadyReacted();
        }

        CommentReactionServiceModel commentReaction = new CommentReactionServiceModel();
        commentReaction.setReactionType(reactionType);
        commentReaction.setUser(user);
        commentReaction.setComment(comment);
        commentReaction.setReactionDate(LocalDateTime.now());

        CommentReactionServiceModel result = this.save(commentReaction, CommentReactionServiceModel.class);

        return result;
    }

    @Override
    public boolean isCommentLikedByUser(UserServiceModel user, CommentServiceModel comment) {
        User entityUser = this.modelMapper.map(user, User.class);
        Comment entityComment = this.modelMapper.map(comment, Comment.class);

        boolean result = super.repository.isUserLikedThatComment(entityUser, entityComment);

        return result;
    }

    @Override
    public int findTotalCommentReactionsByUserAndType(UserServiceModel user, ReactionType reactionType) {
        User entityUser = this.modelMapper.map(user, User.class);
        return super.repository.getUserTotalReactions(entityUser, reactionType);
    }
}
