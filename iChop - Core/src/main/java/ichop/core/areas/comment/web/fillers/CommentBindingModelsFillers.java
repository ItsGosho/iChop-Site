package ichop.core.areas.comment.web.fillers;

import ichop.core.areas.comment.models.binding.CommentCreateBindingModel;
import ichop.core.areas.comment.web.controllers.CommentController;
import ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import ichop.core.areas.thread.services.ThreadServices;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.areas.user.services.UserServices;
import ichop.core.validators.aspects.SkipOnNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@ControllerAdvice(assignableTypes = CommentController.class)
public class CommentBindingModelsFillers {

    private final ThreadServices threadServices;
    private final UserServices userServices;

    @Autowired
    public CommentBindingModelsFillers(ThreadServices threadServices, UserServices userServices) {
        this.threadServices = threadServices;
        this.userServices = userServices;
    }

    @SkipOnNull
    @ModelAttribute
    public CommentCreateBindingModel fill(CommentCreateBindingModel bindingModel, @PathVariable(required = false) String threadId, Principal principal) {
        UserServiceModel creator = this.userServices.findUserByUsername(principal.getName());
        ThreadServiceModel thread = this.threadServices.findById(threadId);

        bindingModel.setCreator(creator);
        bindingModel.setThread(thread);

        return bindingModel;
    }

}
