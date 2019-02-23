package ichop.service.thread.view;

import ichop.domain.entities.threads.Thread;
import ichop.domain.models.service.ThreadServiceModel;
import ichop.domain.models.service.UserServiceModel;
import ichop.domain.models.view.thread.ThreadHomepageViewModel;
import ichop.domain.models.view.thread.ThreadReadViewModel;
import ichop.service.thread.crud.CommentCrudServices;
import ichop.service.thread.crud.ThreadCrudServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ThreadViewServicesImp implements ThreadViewServices {

    private final ThreadCrudServices threadCrudServices;
    private final CommentCrudServices commentCrudServices;
    private final ModelMapper modelMapper;

    @Autowired
    public ThreadViewServicesImp(ThreadCrudServices threadCrudServices, CommentCrudServices commentCrudServices, ModelMapper modelMapper) {
        this.threadCrudServices = threadCrudServices;
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
            threadHomepageViewModel.setTotalReactions(x.getReacts().size());

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
        threadReadViewModel.setTotalReactions(threadServiceModel.getReacts().size());
        threadReadViewModel.setCreatorTotalComments(this.commentCrudServices.getTotalCommentsOfUser(this.modelMapper.map(threadServiceModel.getCreator(), UserServiceModel.class)));

        return threadReadViewModel;
    }

}
