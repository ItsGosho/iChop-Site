package ichop.core.areas.thread.controllers;

import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.thread.constants.ThreadRoutingConstants;
import ichop.core.areas.thread.models.jms.create.ThreadCreateReply;
import ichop.core.areas.thread.models.jms.create.ThreadCreateRequest;
import ichop.core.areas.thread.models.jms.delete.ThreadDeleteByIdReply;
import ichop.core.areas.thread.models.jms.increase.ThreadIncreaseViewsReply;
import ichop.core.areas.thread.models.jms.retrieve.ThreadFindByIdReply;
import ichop.core.areas.thread.requester.ThreadRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class ThreadController {

    private final ResponseHelpers responseHelpers;
    private final ThreadRequester threadRequester;

    @Autowired
    public ThreadController(ResponseHelpers responseHelpers, ThreadRequester threadRequester) {
        this.responseHelpers = responseHelpers;
        this.threadRequester = threadRequester;
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(ThreadRoutingConstants.CREATE)
    public ResponseEntity create(ThreadCreateRequest request, Principal principal) {
        request.setCreatorUsername(principal.getName());

        ThreadCreateReply reply = this.threadRequester.create(request);

        return this.responseHelpers.respondGeneric(reply);
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(ThreadRoutingConstants.DELETE)
    public ResponseEntity delete(@PathVariable String id) {
        ThreadDeleteByIdReply reply = this.threadRequester.deleteById(id);

        return this.responseHelpers.respondGeneric(reply);
    }

    @PostMapping(ThreadRoutingConstants.INCREASE_VIEWS)
    public ResponseEntity increaseViews(@PathVariable String id) {
        ThreadIncreaseViewsReply reply = this.threadRequester.increaseViews(id);

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(ThreadRoutingConstants.FIND_BY)
    public ResponseEntity findBy(@RequestParam(required = true) String id) {
        ThreadFindByIdReply reply = this.threadRequester.findById(id);

        return this.responseHelpers.respondGeneric(reply);
    }

}
