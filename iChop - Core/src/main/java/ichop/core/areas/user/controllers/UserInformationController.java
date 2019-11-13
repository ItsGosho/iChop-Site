package ichop.core.areas.user.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.user.constants.UserRoutingConstants;
import ichop.core.areas.user.models.jms.information.UserInformationUpdateRequest;
import ichop.core.areas.user.models.view.UserInformationViewModel;
import ichop.core.areas.user.requesters.UserInformationRequester;
import org.ichop.commons.domain.JmsReplyModel;
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

    private final UserInformationRequester userInformationRequester;
    private final ResponseHelpers responseHelpers;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserInformationController(UserInformationRequester userInformationRequester,
                                     ResponseHelpers responseHelpers,
                                     ObjectMapper objectMapper) {
        this.userInformationRequester = userInformationRequester;
        this.responseHelpers = responseHelpers;
        this.objectMapper = objectMapper;
    }


    @PreAuthorize("hasAuthority('OWNER') or principal.username.equals(#username)")
    @PostMapping(UserRoutingConstants.UPDATE_INFORMATION)
    public ResponseEntity update(@PathVariable String username,@RequestBody UserInformationUpdateRequest request) {
        request.setUsername(username);

        JmsReplyModel reply = this.userInformationRequester.update(request);

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(UserRoutingConstants.RETRIEVE_INFORMATION)
    public ResponseEntity retrieve(@PathVariable String username) {
        JmsReplyModel reply = this.userInformationRequester.retrieve(username);

        if (reply.isSuccessful()) {
            UserInformationViewModel data = this.objectMapper.convertValue(reply.getData(),UserInformationViewModel.class);
            reply.setData(data);
        }

        return this.responseHelpers.respondGeneric(reply);
    }

}
