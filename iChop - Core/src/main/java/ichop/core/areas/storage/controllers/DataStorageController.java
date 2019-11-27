package ichop.core.areas.storage.controllers;

import ichop.core.areas.storage.models.jms.UserSetAvatarRequest;
import ichop.core.areas.storage.requesters.DataStorageRequester;
import ichop.core.areas.user.constants.UserRoutingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class DataStorageController {

    private final DataStorageRequester storageRequester;

    @Autowired
    public DataStorageController(DataStorageRequester storageRequester) {
        this.storageRequester = storageRequester;
    }


    @PreAuthorize("hasAuthority('OWNER') or #username == authentication.principal")
    @PostMapping(UserRoutingConstants.SET_USER_AVATAR)
    public void setUserAvatar(@PathVariable String username, @RequestBody UserSetAvatarRequest request) {
        request.setUsername(username);

        this.storageRequester.setUserAvatar(request);
    }
}
