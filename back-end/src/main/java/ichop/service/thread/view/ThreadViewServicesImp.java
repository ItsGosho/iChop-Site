package ichop.service.thread.view;

import ichop.domain.entities.threads.Thread;
import ichop.domain.entities.reaction.ReactionType;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.domain.models.view.home.ThreadHomepageViewModel;
import ichop.domain.models.view.thread_read.ThreadReadViewModel;
import ichop.service.comment.crud.CommentCrudServices;
import ichop.service.thread.crud.ThreadCrudServices;
import ichop.service.user.crud.UserCrudServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ThreadViewServicesImp implements ThreadViewServices {

    private final ThreadCrudServices threadCrudServices;
    private final UserCrudServices userCrudServices;
    private final CommentCrudServices commentCrudServices;
    private final ModelMapper modelMapper;

    @Autowired
    public ThreadViewServicesImp(ThreadCrudServices threadCrudServices, UserCrudServices userCrudServices, CommentCrudServices commentCrudServices, ModelMapper modelMapper) {
        this.threadCrudServices = threadCrudServices;
        this.userCrudServices = userCrudServices;
        this.commentCrudServices = commentCrudServices;
        this.modelMapper = modelMapper;
    }


    @Override
    public Page<ThreadHomepageViewModel> listAllByPage(Pageable pageable) {
        Page<Thread> page = this.threadCrudServices.findAll(pageable);

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
    public ThreadReadViewModel getThread(String id) {

        ThreadServiceModel threadServiceModel = this.threadCrudServices.getThread(id);

        ThreadReadViewModel threadReadViewModel = this.modelMapper.map(threadServiceModel, ThreadReadViewModel.class);
        threadReadViewModel.setTotalViews(threadServiceModel.getViews());
        threadReadViewModel.setTotalComments(threadServiceModel.getComments().size());
        threadReadViewModel.setTotalReactions(threadServiceModel.getReactions().size());
        threadReadViewModel.setCreatorTotalComments(this.commentCrudServices.getTotalCommentsOfUser(this.modelMapper.map(threadServiceModel.getCreator(), UserServiceModel.class)));

        threadReadViewModel.getComments().forEach(x->{

            UserServiceModel commentCreator = this.userCrudServices.getUserByUsername(x.getCreatorUsername());
            CommentServiceModel actualComment = threadServiceModel.getComments().stream()
                    .filter(com -> com.getId().equals(x.getId())).findFirst().orElse(null);

            x.setCreatorTotalComments(this.commentCrudServices.getTotalCommentsOfUser(commentCreator));
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
