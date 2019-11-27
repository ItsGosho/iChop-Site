package ichop.storage.web;

import ichop.storage.helpers.UserDataHelper;
import ichop.storage.constants.URLConstants;
import ichop.storage.models.UserSetAvatarRequest;
import org.ichop.commons.rest.helpers.ResponseHelpers;
import org.ichop.commons.validation.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class DataRestController {

    private final UserDataHelper userDataHelper;
    private final ValidationHelper validationHelper;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public DataRestController(UserDataHelper userDataHelper, ValidationHelper validationHelper, ResponseHelpers responseHelpers) {
        this.userDataHelper = userDataHelper;
        this.validationHelper = validationHelper;
        this.responseHelpers = responseHelpers;
    }

    @GetMapping(value = URLConstants.GET_AVATAR, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getAvatar(@PathVariable String username) {
        return this.userDataHelper.getAvatarAsBase64Array(username);
    }

    @PreAuthorize("hasAuthority('OWNER') or #username == authentication.principal")
    @PostMapping(value = URLConstants.SET_AVATAR)
    public ResponseEntity setUserAvatar(@PathVariable String username, @RequestBody UserSetAvatarRequest request) {
        request.setUsername(username);

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        this.userDataHelper.updateAvatar(request.getUsername(), request.getAvatar());

        return this.responseHelpers.respondSuccessful("Avatar updated!");
    }
}
