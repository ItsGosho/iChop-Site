package ichop.core.areas.reaction.services;

import ichop.core.areas.comment.domain.entities.Comment;
import ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import ichop.core.areas.comment.exceptions.CommentNotFoundException;
import ichop.core.areas.reaction.domain.entities.CommentReaction;
import ichop.core.areas.reaction.domain.entities.ReactionType;
import ichop.core.areas.reaction.domain.models.binding.CommentReactionCreateBindingModel;
import ichop.core.areas.reaction.domain.models.service.CommentReactionServiceModel;
import ichop.core.areas.reaction.exceptions.CantReactException;
import ichop.core.areas.reaction.repositories.CommentReactionRepository;
import ichop.core.areas.user.domain.entities.User;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.areas.user.exceptions.UserAlreadyReacted;
import ichop.core.areas.user.exceptions.UserNotFoundException;
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
    public CommentReactionServiceModel create(CommentReactionCreateBindingModel bindingModel) {

        if (bindingModel.getComment() == null) {
            throw new CommentNotFoundException();
        }

        if (bindingModel.getUser() == null) {
            throw new UserNotFoundException();
        }

        if (this.isReactedByUser(bindingModel.getUser(), bindingModel.getComment())) {
            throw new UserAlreadyReacted();
        }

        if(bindingModel.getComment().getCreator().getUsername().equals(bindingModel.getUser().getUsername())){
            throw new CantReactException();
        }

        CommentReactionServiceModel commentReaction = this.modelMapper.map(bindingModel,CommentReactionServiceModel.class);
        commentReaction.setReactionDate(LocalDateTime.now());

        CommentReactionServiceModel result = this.save(commentReaction, CommentReactionServiceModel.class);

        return result;
    }

    @Override
    public boolean isReactedByUser(UserServiceModel user, CommentServiceModel comment) {

        if(user == null) {
            throw new UserNotFoundException();
        }
        if(comment == null) {
            throw new CommentNotFoundException();
        }

        User entityUser = this.modelMapper.map(user, User.class);
        Comment entityComment = this.modelMapper.map(comment, Comment.class);

        boolean result = this.repository.isUserReactedAtThatComment(entityUser, entityComment);

        return result;
    }

    @Override
    public int findTotalReactionsByUserAndType(UserServiceModel user, ReactionType reactionType) {

        if(user == null) {
            throw new UserNotFoundException();
        }

        User entityUser = this.modelMapper.map(user, User.class);
        return this.repository.getUserTotalReactions(entityUser, reactionType);
    }
}
