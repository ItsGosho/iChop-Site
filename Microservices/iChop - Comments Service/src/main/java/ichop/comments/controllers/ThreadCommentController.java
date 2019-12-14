package ichop.comments.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.comments.constants.CommentReplyConstants;
import ichop.comments.constants.CommentRoutingConstants;
import ichop.comments.domain.enums.Type;
import ichop.comments.domain.models.jms.create.ThreadCommentCreateRequest;
import ichop.comments.domain.models.jms.delete.CommentDeleteByIdRequest;
import ichop.comments.domain.models.service.ThreadCommentServiceModel;
import ichop.comments.domain.models.service.UserProfileCommentServiceModel;
import ichop.comments.requesters.ThreadRequester;
import ichop.comments.services.GenericCommentServices;
import ichop.comments.services.ThreadCommentServices;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.requesters.UserRequester;
import org.ichop.commons.rest.helpers.ResponseHelpers;
import org.ichop.commons.validation.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ThreadCommentController {

    private final ThreadCommentServices threadCommentServices;
    private final GenericCommentServices genericCommentServices;
    private final ResponseHelpers responseHelpers;
    private final ThreadRequester threadRequester;
    private final ValidationHelper validationHelper;
    private final ObjectMapper objectMapper;

    @Autowired
    public ThreadCommentController(ThreadCommentServices threadCommentServices,
                                   GenericCommentServices genericCommentServices,
                                   ResponseHelpers responseHelpers,
                                   ThreadRequester threadRequester,
                                   ValidationHelper validationHelper,
                                   ObjectMapper objectMapper) {
        this.genericCommentServices = genericCommentServices;
        this.responseHelpers = responseHelpers;
        this.threadCommentServices = threadCommentServices;
        this.threadRequester = threadRequester;
        this.validationHelper = validationHelper;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(CommentRoutingConstants.THREAD_CREATE)
    public ResponseEntity create(@RequestBody ThreadCommentCreateRequest request, Principal principal) {
        request.setCreatorUsername(principal.getName());

        JmsReplyModel threadReply = this.threadRequester.findById(request.getThreadId());

        if (threadReply.isSuccessful()) {

            if (!this.validationHelper.isValid(request)) {
                String error = this.validationHelper.getValidationError(request);
                return this.responseHelpers.respondError(error);
            }

            ThreadCommentServiceModel serviceModel = this.objectMapper.convertValue(request, ThreadCommentServiceModel.class);
            threadCommentServices.save(serviceModel);

            return this.responseHelpers.respondSuccessful(CommentReplyConstants.COMMENT_CREATED_SUCCESSFUL);
        }

        return this.responseHelpers.respondGeneric(threadReply);
    }

    @PreAuthorize("hasAuthority('MODERATOR') or @baseCommentRequesterImp.isCreator(#commentId,authentication.principal,'THREAD') == true")
    @PostMapping(CommentRoutingConstants.THREAD_DELETE)
    public ResponseEntity delete(@PathVariable String threadId, @PathVariable String commentId, Principal principal) {

        JmsReplyModel threadReply = this.threadRequester.findById(threadId);

        if (threadReply.isSuccessful()) {
            CommentDeleteByIdRequest request = new CommentDeleteByIdRequest();
            request.setId(commentId);
            request.setType(Type.THREAD);

            if (!this.validationHelper.isValid(request)) {
                String error = this.validationHelper.getValidationError(request);
                return this.responseHelpers.respondError(error);
            }


            this.genericCommentServices.deleteById(commentId, Type.THREAD);
            return this.responseHelpers.respondSuccessful(CommentReplyConstants.COMMENT_DELETE_SUCCESSFUL);
        }

        return this.responseHelpers.respondGeneric(threadReply);
    }

    @GetMapping(CommentRoutingConstants.THREAD_ALL)
    public ResponseEntity findBy(@PathVariable String threadId) {

        JmsReplyModel threadReply = this.threadRequester.findById(threadId);

        if (threadReply.isSuccessful()) {
            List<ThreadCommentServiceModel> result = this.threadCommentServices.findAllByThreadId(threadId)
                    .stream()
                    .sorted((x1, x2) -> x2.getCreatedOn().compareTo(x1.getCreatedOn()))
                    .collect(Collectors.toList());
            return this.responseHelpers.respondSuccessful(CommentReplyConstants.FETCH_SUCCESSFUL, result);
        }

        return this.responseHelpers.respondGeneric(threadReply);
    }
}
