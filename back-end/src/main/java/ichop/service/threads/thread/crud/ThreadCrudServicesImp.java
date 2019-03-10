package ichop.service.threads.thread.crud;

import ichop.domain.entities.threads.Thread;
import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.threads.thread.ThreadServiceModel;
import ichop.repository.threads.thread.ThreadRepository;
import ichop.service.threads.comment.crud.CommentCrudServices;
import ichop.service.threads.reaction.crud.ReactionCrudServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThreadCrudServicesImp implements ThreadCrudServices {


    private final ThreadRepository threadRepository;
    private final CommentCrudServices commentCrudServices;
    private final ReactionCrudServices reactionCrudServices;
    private final ModelMapper modelMapper;

    @Autowired
    public ThreadCrudServicesImp(ThreadRepository threadRepository, CommentCrudServices commentCrudServices, ReactionCrudServices reactionCrudServices, ModelMapper modelMapper) {
        this.threadRepository = threadRepository;
        this.commentCrudServices = commentCrudServices;
        this.reactionCrudServices = reactionCrudServices;
        this.modelMapper = modelMapper;
    }


    @Override
    public ThreadServiceModel getThread(String id) {
        Thread entityThread = this.threadRepository.findThreadById(id);

        if(entityThread != null){
            return this.modelMapper.map(entityThread,ThreadServiceModel.class);
        }

        return null;
    }

    @Override
    public void delete(String id) {
        Thread entityThread = this.threadRepository.findThreadById(id);

        this.threadRepository.delete(entityThread);
    }

    @Override
    public boolean exists(String id) {
        Thread entityThread = this.threadRepository.findThreadById(id);

        if (entityThread != null) {
            return true;
        }

        return false;
    }

    @Override
    public ThreadServiceModel save(ThreadServiceModel thread) {
        Thread entityThread = this.modelMapper.map(thread,Thread.class);
        this.threadRepository.save(entityThread);
        return this.modelMapper.map(entityThread,ThreadServiceModel.class);
    }

    @Override
    public List<ThreadServiceModel> findAll() {
        List<ThreadServiceModel> result =
                this.threadRepository.findAll()
                .stream()
                .map(x-> this.modelMapper.map(x,ThreadServiceModel.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public Page<Thread> findAll(Pageable pageable) {
        Page<Thread> result =
                this.threadRepository.findAll(pageable);

        return result;
    }

    @Override
    public void increaseViews(String id) {
        this.threadRepository.increaseViews(id);
    }

}
