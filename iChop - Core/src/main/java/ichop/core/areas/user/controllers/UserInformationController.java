package ichop.core.areas.user.controllers;

import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.user.constants.UserRoutingConstants;
import ichop.core.areas.user.models.jms.information.UserInformationRetrieveReply;
import ichop.core.areas.user.models.jms.information.UserInformationUpdateReply;
import ichop.core.areas.user.models.jms.information.UserInformationUpdateRequest;
import ichop.core.areas.user.requester.UserInformationRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserInformationController {

    private final UserInformationRequester userInformationRequester;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public UserInformationController(UserInformationRequester userInformationRequester, ResponseHelpers responseHelpers) {
        this.userInformationRequester = userInformationRequester;
        this.responseHelpers = responseHelpers;
    }


    @PreAuthorize("hasAuthority('OWNER') or principal.username.equals(#username)")
    @PostMapping(UserRoutingConstants.UPDATE_INFORMATION)
    public ResponseEntity update(@PathVariable String username, UserInformationUpdateRequest request) {
        request.setUsername(username);

        UserInformationUpdateReply reply = this.userInformationRequester.update(request);

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(UserRoutingConstants.RETRIEVE_INFORMATION)
    public ResponseEntity retrieve(@PathVariable String username) {
        UserInformationRetrieveReply reply = this.userInformationRequester.retrieve(username);

        return this.responseHelpers.respondGeneric(reply);
    }

}
