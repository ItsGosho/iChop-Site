package ichop.storage.web;

import ichop.storage.helpers.UserDataHelper;
import ichop.storage.constants.URLConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataRestController {

    private final UserDataHelper userDataHelper;

    @Autowired
    public DataRestController(UserDataHelper userDataHelper) {
        this.userDataHelper = userDataHelper;
    }

    @GetMapping(value = URLConstants.GET_AVATAR, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getAvatar(@PathVariable String username) {
        return this.userDataHelper.getAvatarAsBase64Array(username);
    }
}
