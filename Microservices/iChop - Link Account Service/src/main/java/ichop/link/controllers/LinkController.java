package ichop.link.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.link.constants.LinkRoutingConstants;
import ichop.link.domain.models.binding.LinkCreateBindingModel;
import ichop.link.domain.models.binding.LinkRemoveBindingModel;
import ichop.link.requesters.KeyRequester;
import ichop.link.requesters.LinkRequester;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.rest.helpers.ResponseHelpers;
import org.ichop.commons.validation.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class LinkController {

    private final ResponseHelpers responseHelpers;
    private final KeyRequester keyRequester;
    private final LinkRequester linkRequester;

    @Autowired
    public LinkController(ResponseHelpers responseHelpers,
                          KeyRequester keyRequester,
                          LinkRequester linkRequester) {
        this.responseHelpers = responseHelpers;
        this.keyRequester = keyRequester;
        this.linkRequester = linkRequester;
    }


    @GetMapping(LinkRoutingConstants.IS_KEY_VALID)
    public ResponseEntity isKeyValid(@RequestParam String key) {
        JmsReplyModel reply = this.keyRequester.isKeyValid(key);

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(LinkRoutingConstants.KEY_RETRIEVE)
    public ResponseEntity retrieveKey(@RequestParam String key) {
        JmsReplyModel reply = this.keyRequester.findKeyByKey(key);

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(LinkRoutingConstants.LINK_RETRIEVE)
    public ResponseEntity linkRetrieve(@RequestParam String username) {
        JmsReplyModel reply = this.linkRequester.linkRetrieve(username);

        return this.responseHelpers.respondGeneric(reply);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(LinkRoutingConstants.LINK_CREATE)
    public ResponseEntity createLink(@RequestBody LinkCreateBindingModel bindingModel, Principal principal) {
        JmsReplyModel reply = this.linkRequester.linkCreate(bindingModel.getLinkKey(), principal.getName());

        return this.responseHelpers.respondGeneric(reply);
    }

    @PreAuthorize("hasAuthority('OWNER') or #bindingModel.username.equals(#principal.name)")
    @PostMapping(LinkRoutingConstants.LINK_REMOVE)
    public ResponseEntity removeLink(@RequestBody LinkRemoveBindingModel bindingModel, Principal principal) {
        JmsReplyModel reply = this.linkRequester.linkRemove(bindingModel.getUsername());

        return this.responseHelpers.respondGeneric(reply);
    }

}
