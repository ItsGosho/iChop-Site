package ichop.link.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.link.constants.LinkRoutingConstants;
import ichop.link.domain.models.binding.LinkCreateBindingModel;
import ichop.link.requesters.KeyRequester;
import ichop.link.requesters.LinkRequester;
import org.ichop.commons.rest.helpers.ResponseHelpers;
import org.ichop.commons.validation.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LinkController {

    private final ResponseHelpers responseHelpers;
    private final ValidationHelper validationHelper;
    private final KeyRequester keyRequester;
    private final LinkRequester linkRequester;
    private final ObjectMapper objectMapper;

    @Autowired
    public LinkController(ResponseHelpers responseHelpers,
                          ValidationHelper validationHelper,
                          KeyRequester keyRequester,
                          LinkRequester linkRequester,
                          ObjectMapper objectMapper) {
        this.responseHelpers = responseHelpers;
        this.validationHelper = validationHelper;
        this.keyRequester = keyRequester;
        this.linkRequester = linkRequester;
        this.objectMapper = objectMapper;
    }


    @GetMapping(LinkRoutingConstants.IS_KEY_VALID)
    private ResponseEntity isKeyValid(@RequestParam String key) {
        return null;
    }

    @GetMapping(LinkRoutingConstants.KEY_RETRIEVE)
    private ResponseEntity retrieveKey(@RequestParam String key) {
        return null;
    }

    @GetMapping(LinkRoutingConstants.LINK_RETRIEVE)
    private ResponseEntity linkRetrieve(@RequestParam String username) {
        return null;
    }

    @PostMapping(LinkRoutingConstants.LINK_CREATE)
    private ResponseEntity createLink(@RequestBody LinkCreateBindingModel bindingModel) {
        return null;
    }

}
