package ichop.threads.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.threads.constants.ThreadReplyConstants;
import ichop.threads.constants.ThreadRoutingConstants;
import ichop.threads.domain.models.jms.ThreadReply;
import ichop.threads.domain.models.jms.create.ThreadCreateRequest;
import ichop.threads.domain.models.jms.delete.ThreadDeleteByIdRequest;
import ichop.threads.domain.models.jms.increase.ThreadIncreaseViewsRequest;
import ichop.threads.domain.models.service.ThreadServiceModel;
import ichop.threads.services.ThreadServices;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.domain.LongReply;
import org.ichop.commons.rest.helpers.ResponseHelpers;
import org.ichop.commons.validation.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ThreadController {

    private final ResponseHelpers responseHelpers;
    private final ThreadServices threadServices;
    private final ValidationHelper validationHelper;
    private final ObjectMapper objectMapper;

    @Autowired
    public ThreadController(ResponseHelpers responseHelpers, ThreadServices threadServices, ValidationHelper validationHelper, ObjectMapper objectMapper) {
        this.responseHelpers = responseHelpers;
        this.threadServices = threadServices;
        this.validationHelper = validationHelper;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(ThreadRoutingConstants.CREATE)
    public ResponseEntity create(@RequestBody ThreadCreateRequest request, Principal principal) {
        request.setCreatorUsername(principal.getName());

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        ThreadServiceModel threadServiceModel = this.objectMapper.convertValue(request, ThreadServiceModel.class);
        this.threadServices.save(threadServiceModel);

        return this.responseHelpers.respondSuccessful(ThreadReplyConstants.THREAD_CREATED_SUCCESSFUL);
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(ThreadRoutingConstants.DELETE)
    public ResponseEntity delete(@PathVariable String id) {
        ThreadDeleteByIdRequest request = new ThreadDeleteByIdRequest();
        request.setId(id);

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        this.threadServices.deleteById(id);

        return this.responseHelpers.respondSuccessful(ThreadReplyConstants.THREAD_DELETED_SUCCESSFUL);
    }

    @PostMapping(ThreadRoutingConstants.INCREASE_VIEWS)
    public ResponseEntity increaseViews(@PathVariable String id) {
        ThreadIncreaseViewsRequest request = new ThreadIncreaseViewsRequest();
        request.setId(id);

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        this.threadServices.increaseViews(id);

        return this.responseHelpers.respondSuccessful(ThreadReplyConstants.THREAD_VIEW_INCREASED_SUCCESSFUL);
    }

    @GetMapping(ThreadRoutingConstants.FIND_BY)
    public ResponseEntity findBy(@RequestParam(required = true) String id) {

        if (!this.threadServices.existsById(id)) {
            return this.responseHelpers.respondError(ThreadReplyConstants.NOT_FOUND);
        }

        ThreadServiceModel thread = this.threadServices.findById(id);

        return this.responseHelpers.respondSuccessful(ThreadReplyConstants.FETCH_SUCCESSFUL, thread);
    }

    @GetMapping(ThreadRoutingConstants.FIND_TOTAL)
    public ResponseEntity findTotal() {
        Long total = this.threadServices.findTotal();
        LongReply reply = new LongReply(total);

        return this.responseHelpers.respondSuccessful(ThreadReplyConstants.FETCH_SUCCESSFUL, reply);
    }

    @GetMapping(ThreadRoutingConstants.FIND_ALL)
    public ResponseEntity findAll(Pageable pageable) {
        List<ThreadServiceModel> threads = this.threadServices.findAll(pageable)
                .stream()
                //.sorted((x1, x2) -> x2.getCreatedOn().compareTo(x1.getCreatedOn()))
                .collect(Collectors.toList());;

        return this.responseHelpers.respondSuccessful(ThreadReplyConstants.FETCH_SUCCESSFUL, threads);
    }
}
