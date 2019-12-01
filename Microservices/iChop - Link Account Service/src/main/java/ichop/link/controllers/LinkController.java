package ichop.link.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ichop.commons.rest.helpers.ResponseHelpers;
import org.ichop.commons.validation.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LinkController {

    private final ResponseHelpers responseHelpers;
    private final ValidationHelper validationHelper;
    private final ObjectMapper objectMapper;

    @Autowired
    public LinkController(ResponseHelpers responseHelpers, ValidationHelper validationHelper, ObjectMapper objectMapper) {
        this.responseHelpers = responseHelpers;
        this.validationHelper = validationHelper;
        this.objectMapper = objectMapper;
    }


}
