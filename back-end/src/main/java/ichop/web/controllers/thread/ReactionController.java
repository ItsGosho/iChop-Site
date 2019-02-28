package ichop.web.controllers.thread;

import ichop.constants.URLConstants;
import ichop.domain.entities.threads.reaction.ReactionType;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.ThreadServiceModel;
import ichop.domain.models.service.UserServiceModel;
import ichop.exceptions.thread.ReactionNotFoundException;
import ichop.exceptions.thread.ThreadNotFoundException;
import ichop.service.thread.ReactServices;
import ichop.service.thread.crud.ThreadCrudServices;
import ichop.web.controllers.BaseController;
import org.apache.commons.lang3.EnumUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class ReactionController extends BaseController {


    private final ThreadCrudServices threadCrudServices;
    private final ReactServices reactServices;
    private final ModelMapper modelMapper;

    @Autowired
    public ReactionController(ThreadCrudServices threadCrudServices, ReactServices reactServices, ModelMapper modelMapper) {
        this.threadCrudServices = threadCrudServices;
        this.reactServices = reactServices;
        this.modelMapper = modelMapper;
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(URLConstants.THREAD_REACTION_LIKE)
    public ModelAndView reactLike(@PathVariable String id, @PathVariable String reactionType, Principal principal) {

        ThreadServiceModel threadServiceModel = this.threadCrudServices.getThread(id);
        UserServiceModel userServiceModel = this.modelMapper.map((User) ((Authentication) principal).getPrincipal(), UserServiceModel.class);

        if (threadServiceModel != null) {

            boolean isReactionTypeValid = EnumUtils.isValidEnum(ReactionType.class, reactionType.toUpperCase());

            if (isReactionTypeValid) {
                ReactionType reactType = ReactionType.valueOf(reactionType.toUpperCase());
                this.reactServices.addReaction(threadServiceModel, userServiceModel, reactType);

                String redirectUrl = URLConstants.THREAD_READ_GET.replace("{id}", threadServiceModel.getId());
                return super.redirect(redirectUrl);
            }

            throw new ReactionNotFoundException();
        }

        throw new ThreadNotFoundException();
    }

}
