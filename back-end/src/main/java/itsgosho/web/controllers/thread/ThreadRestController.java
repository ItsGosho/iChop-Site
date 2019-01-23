package itsgosho.web.controllers.thread;

import itsgosho.domain.models.binding.thread.ThreadCreateBindingModel;
import itsgosho.service.thread.ThreadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/thread")
public class ThreadRestController {

    private final ThreadServices threadServices;

    @Autowired
    public ThreadRestController(ThreadServices threadServices) {
        this.threadServices = threadServices;
    }

    //@PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/create")
    @ResponseBody
    public String threadCreate(ThreadCreateBindingModel threadCreateBindingModel, Principal principal) {
        this.threadServices.createThread(threadCreateBindingModel,principal.getName());
        return "";
    }
}
