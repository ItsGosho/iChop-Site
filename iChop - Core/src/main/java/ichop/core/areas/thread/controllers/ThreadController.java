package ichop.core.areas.thread.controllers;

import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.thread.constants.ThreadRoutingConstants;
import ichop.core.areas.thread.models.jms.create.ThreadCreateRequest;
import ichop.core.areas.thread.requesters.ThreadRequester;
import org.ichop.commons.domain.JmsReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity create(@RequestBody ThreadCreateRequest request, Principal principal) {
        request.setCreatorUsername(principal.getName());

        JmsReplyModel reply = this.threadRequester.create(request);

        return this.responseHelpers.respondGeneric(reply);
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(ThreadRoutingConstants.DELETE)
    public ResponseEntity delete(@PathVariable String id) {
        JmsReplyModel reply = this.threadRequester.deleteById(id);

        return this.responseHelpers.respondGeneric(reply);
    }

    @PostMapping(ThreadRoutingConstants.INCREASE_VIEWS)
    public ResponseEntity increaseViews(@PathVariable String id) {
        JmsReplyModel reply = this.threadRequester.increaseViews(id);

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(ThreadRoutingConstants.FIND_BY)
    public ResponseEntity findBy(@RequestParam(required = true) String id) {
        JmsReplyModel reply = this.threadRequester.findById(id);

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(ThreadRoutingConstants.FIND_TOTAL)
    public ResponseEntity findTotal() {
        JmsReplyModel reply = this.threadRequester.findTotal();

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(ThreadRoutingConstants.FIND_ALL)
    public ResponseEntity findAll(Pageable pageable) {
        JmsReplyModel reply = this.threadRequester.findAll(pageable);

        return this.responseHelpers.respondGeneric(reply);
    }

}
