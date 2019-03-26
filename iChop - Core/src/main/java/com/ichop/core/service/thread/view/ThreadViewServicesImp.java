package com.ichop.core.service.thread.view;

import com.ichop.core.domain.entities.reaction.ReactionType;
import com.ichop.core.domain.models.service.comment.CommentServiceModel;
import com.ichop.core.domain.models.service.thread.ThreadServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.domain.models.view.home.ThreadHomepageViewModel;
import com.ichop.core.domain.models.view.thread_read.ThreadReadViewModel;
import com.ichop.core.service.comment.CommentServices;
import com.ichop.core.service.player.PlayerServices;
import com.ichop.core.service.thread.ThreadServices;
import com.ichop.core.service.user.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ThreadViewServicesImp implements ThreadViewServices {

    private final ThreadServices threadServices;
    private final UserServices userServices;
    private final CommentServices commentServices;
    private final ModelMapper modelMapper;
    private final PlayerServices playerServices;

    @Autowired
    public ThreadViewServicesImp(ThreadServices threadServices, UserServices userServices, CommentServices commentServices, ModelMapper modelMapper, PlayerServices playerServices) {
        this.threadServices = threadServices;
        this.userServices = userServices;
        this.commentServices = commentServices;
        this.modelMapper = modelMapper;
        this.playerServices = playerServices;
    }

    @Override
    public Page<ThreadHomepageViewModel> listAllByPage(Pageable pageable) {
        Page<ThreadServiceModel> page = this.threadServices.findAllThreads(pageable);

        Page<ThreadHomepageViewModel> threads = page.map(x -> {
            ThreadHomepageViewModel threadHomepageViewModel = this.modelMapper.map(x, ThreadHomepageViewModel.class);
            threadHomepageViewModel.setTotalViews(x.getViews());
            threadHomepageViewModel.setTotalComments(x.getComments().size());
            threadHomepageViewModel.setTotalReactions(x.getReactions().size());

            return threadHomepageViewModel;
        });
        return threads;
    }

    @Override
    public ThreadReadViewModel getThreadById(String id) {

        ThreadServiceModel threadServiceModel = this.threadServices.findThreadById(id);

        ThreadReadViewModel threadReadViewModel = this.modelMapper.map(threadServiceModel, ThreadReadViewModel.class);
        threadReadViewModel.setTotalViews(threadServiceModel.getViews());
        threadReadViewModel.setTotalComments(threadServiceModel.getComments().size());
        threadReadViewModel.setTotalReactions(threadServiceModel.getReactions().size());
        threadReadViewModel.setCreatorTotalComments(this.commentServices.getTotalCommentsOfUser(this.modelMapper.map(threadServiceModel.getCreator(), UserServiceModel.class)));
        threadReadViewModel.setMinecraftAccountName((String) this.playerServices.getPlayerDataBySiteUser(threadReadViewModel.getCreatorUsername()).getPlayerName());

        threadReadViewModel.getComments().forEach(x -> {

            UserServiceModel commentCreator = this.userServices.findUserByUsername(x.getCreatorUsername());
            CommentServiceModel actualComment = threadServiceModel.getComments().stream()
                    .filter(com -> com.getId().equals(x.getId())).findFirst().orElse(null);

            x.setCreatorTotalComments(this.commentServices.getTotalCommentsOfUser(commentCreator));
            x.setTotalLikes((int) actualComment.getReactions().
                    stream().
                    filter(react -> react.getReactionType().equals(ReactionType.LIKE)).count());
            x.setTotalDislikes((int) actualComment.getReactions().
                    stream().
                    filter(react -> react.getReactionType().equals(ReactionType.DISLIKE)).count());
            x.setMinecraftAccountName((String) this.playerServices.getPlayerDataBySiteUser(threadReadViewModel.getCreatorUsername()).getPlayerName());
        });

        threadReadViewModel.setComments(threadReadViewModel.getComments().stream()
                .sorted((x1, x2) -> x2.getCreatedOn().compareTo(x1.getCreatedOn())).collect(Collectors.toList()));

        return threadReadViewModel;
    }

}
