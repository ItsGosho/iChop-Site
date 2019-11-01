package ichop.core.areas.user.controllers;

import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.user.requester.UserRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAdminController {

    private final UserRequester userRequester;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public UserAdminController(UserRequester userRequester, ResponseHelpers responseHelpers) {
        this.userRequester = userRequester;
        this.responseHelpers = responseHelpers;
    }



}
