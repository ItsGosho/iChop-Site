package ichop.link.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.link.constants.LinkRoutingConstants;
import ichop.link.domain.models.binding.LinkCreateBindingModel;
import ichop.link.requesters.KeyRequester;
import ichop.link.requesters.LinkRequester;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.rest.helpers.ResponseHelpers;
import org.ichop.commons.validation.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LinkController {

    private final ResponseHelpers responseHelpers;
    private final KeyRequester keyRequester;
    private final LinkRequester linkRequester;

    @Autowired
    public LinkController(ResponseHelpers responseHelpers,
                          ValidationHelper validationHelper,
                          KeyRequester keyRequester,
                          LinkRequester linkRequester,
                          ObjectMapper objectMapper) {
        this.responseHelpers = responseHelpers;
        this.keyRequester = keyRequester;
        this.linkRequester = linkRequester;
    }


    @GetMapping(LinkRoutingConstants.IS_KEY_VALID)
    private ResponseEntity isKeyValid(@RequestParam String key) {
        JmsReplyModel reply = this.keyRequester.isKeyValid(key);

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(LinkRoutingConstants.KEY_RETRIEVE)
    private ResponseEntity retrieveKey(@RequestParam String key) {
        JmsReplyModel reply = this.keyRequester.findKeyByKey(key);

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(LinkRoutingConstants.LINK_RETRIEVE)
    private ResponseEntity linkRetrieve(@RequestParam String username) {
        JmsReplyModel reply = this.linkRequester.linkRetrieve(username);

        return this.responseHelpers.respondGeneric(reply);
    }

    @PostMapping(LinkRoutingConstants.LINK_CREATE)
    private ResponseEntity createLink(@RequestBody LinkCreateBindingModel bindingModel) {
        JmsReplyModel reply = this.linkRequester.linkCreate(bindingModel.getLinkKey(), bindingModel.getUsername());

        return this.responseHelpers.respondGeneric(reply);
    }

}
