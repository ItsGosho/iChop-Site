package com.ichop.core.areas.reaction.services;

import com.ichop.core.areas.comment.domain.entities.Comment;
import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.reaction.domain.entities.CommentReaction;
import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.domain.models.service.CommentReactionServiceModel;
import com.ichop.core.areas.reaction.exceptions.CantReactException;
import com.ichop.core.areas.reaction.repositories.CommentReactionRepository;
import com.ichop.core.areas.thread.exceptions.CommentNotFoundException;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserAlreadyReacted;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
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

        if (user == null) {
            throw new UserNotFoundException();
        }

        if (this.isCommentLikedByUser(user, comment)) {
            throw new UserAlreadyReacted();
        }

        if(comment.getCreator().getUsername().equals(user.getUsername())){
            throw new CantReactException();
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

    @Override
    public boolean isReactedByUser(CommentServiceModel comment,UserServiceModel user) {

        if(comment == null ){
            throw new CommentNotFoundException();
        }

        if(user == null){
            throw new UserNotFoundException();
        }

        return super.repository.isUserLikedThatComment(super.modelMapper.map(user,User.class),super.modelMapper.map(comment,Comment.class));
    }
}
