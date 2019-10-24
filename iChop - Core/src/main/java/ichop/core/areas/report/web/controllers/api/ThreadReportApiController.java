package ichop.core.areas.report.web.controllers.api;

import com.google.gson.Gson;
import ichop.core.areas.report.services.ThreadReportServices;
import ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import ichop.core.areas.thread.services.ThreadServices;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.areas.user.services.UserServices;
import ichop.core.base.BaseController;
import ichop.core.constants.URLConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ThreadReportApiController extends BaseController {

    private final ThreadServices threadServices;
    private final ThreadReportServices threadReportServices;
    private final UserServices userServices;

    @Autowired
    public ThreadReportApiController(ThreadServices threadServices, ThreadReportServices threadReportServices, UserServices userServices) {
        this.threadServices = threadServices;
        this.threadReportServices = threadReportServices;
        this.userServices = userServices;
    }

    @GetMapping(value = URLConstants.IS_THREAD_ALREADY_REPORTED_BY_USER, produces = "application/json")
    @ResponseBody
    public String isThreadAlreadyReportedByUser(@PathVariable String threadId, @RequestParam String userUsername) {
        ThreadServiceModel thread = this.threadServices.findById(threadId);
        UserServiceModel user = this.userServices.findUserByUsername(userUsername);

        if (thread == null || user == null) {
            return new Gson().toJson(false);
        }

        if (thread.getCreator().getUsername().equals(user.getUsername())) {
            return new Gson().toJson(true);
        }

        boolean isReported = this.threadReportServices.isReportedByUser(user, thread);
        return new Gson().toJson(isReported);
    }
}
