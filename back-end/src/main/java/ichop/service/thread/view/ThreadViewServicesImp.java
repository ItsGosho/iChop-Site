package ichop.service.thread.view;

import ichop.domain.entities.reaction.ReactionType;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.domain.models.view.home.ThreadHomepageViewModel;
import ichop.domain.models.view.thread_read.ThreadReadViewModel;
import ichop.service.comment.CommentServices;
import ichop.service.thread.ThreadServices;
import ichop.service.user.UserServices;
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

    @Autowired
    public ThreadViewServicesImp(ThreadServices threadServices, UserServices userServices, CommentServices commentServices, ModelMapper modelMapper) {
        this.threadServices = threadServices;
        this.userServices = userServices;
        this.commentServices = commentServices;
        this.modelMapper = modelMapper;
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

        threadReadViewModel.getComments().forEach(x->{

            UserServiceModel commentCreator = this.userServices.findUserByUsername(x.getCreatorUsername());
            CommentServiceModel actualComment = threadServiceModel.getComments().stream()
                    .filter(com -> com.getId().equals(x.getId())).findFirst().orElse(null);

            x.setCreatorTotalComments(this.commentServices.getTotalCommentsOfUser(commentCreator));
            x.setTotalLikes((int) actualComment.getReactions().
                    stream().
                    filter(react->react.getReactionType().equals(ReactionType.LIKE)).count());
            x.setTotalDislikes((int) actualComment.getReactions().
                    stream().
                    filter(react->react.getReactionType().equals(ReactionType.DISLIKE)).count());
        });

        threadReadViewModel.setComments(threadReadViewModel.getComments().stream()
        .sorted((x1,x2)->x2.getCreatedOn().compareTo(x1.getCreatedOn())).collect(Collectors.toList()));

        return threadReadViewModel;
    }

}
