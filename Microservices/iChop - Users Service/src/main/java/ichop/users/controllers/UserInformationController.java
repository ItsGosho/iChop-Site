package ichop.users.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.constants.UserRoutingConstants;
import ichop.users.domain.models.jms.information.UserInformationRetrieveRequest;
import ichop.users.domain.models.jms.information.UserInformationUpdateRequest;
import ichop.users.domain.models.service.UserInformationServiceModel;
import ichop.users.domain.models.view.UserInformationViewModel;
import ichop.users.services.UserInformationServices;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.rest.helpers.ResponseHelpers;
import org.ichop.commons.validation.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserInformationController {

    private final UserInformationServices userInformationServices;
    private final ResponseHelpers responseHelpers;
    private final ObjectMapper objectMapper;
    private final ValidationHelper validationHelper;

    @Autowired
    public UserInformationController(UserInformationServices userInformationServices,
                                     ResponseHelpers responseHelpers,
                                     ObjectMapper objectMapper,
                                     ValidationHelper validationHelper) {
        this.userInformationServices = userInformationServices;
        this.responseHelpers = responseHelpers;
        this.objectMapper = objectMapper;
        this.validationHelper = validationHelper;
    }


    @PreAuthorize("hasAuthority('OWNER') or #username == authentication.principal")
    @PostMapping(UserRoutingConstants.UPDATE_INFORMATION)
    public ResponseEntity update(@PathVariable String username, @RequestBody UserInformationUpdateRequest request) {
        request.setUsername(username);

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        this.userInformationServices.update(request);

        return this.responseHelpers.respondSuccessful("Information updated successful!");
    }

    @GetMapping(UserRoutingConstants.RETRIEVE_INFORMATION)
    public ResponseEntity retrieve(@PathVariable String username) {
        UserInformationRetrieveRequest request = new UserInformationRetrieveRequest();
        request.setUsername(username);

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        UserInformationServiceModel informationServiceModel = this.userInformationServices.getByUser(username);
        UserInformationViewModel viewModel = this.objectMapper.convertValue(informationServiceModel, UserInformationViewModel.class);

        return this.responseHelpers.respondSuccessful("Fetch successful!", viewModel);
    }

}
