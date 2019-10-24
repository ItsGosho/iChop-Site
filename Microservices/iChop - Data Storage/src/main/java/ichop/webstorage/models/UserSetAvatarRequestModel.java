package ichop.webstorage.models;

import ichop.webstorage.common.domain.BaseRequestModel;
import ichop.webstorage.validators.Base64;
import ichop.webstorage.validators.Base64Image;
import ichop.webstorage.validators.Base64Size;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static ichop.webstorage.constants.ValidationMessages.*;

@Getter
@Setter
public class UserSetAvatarRequestModel extends BaseRequestModel {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    @Base64(message = BASE_64)
    @Base64Size(maxInMB = 1.00,message = AVATAR_SIZE_TOO_BIG)
    @Base64Image(maxHeight = 200,maxWidth = 200,message = AVATAR_WIDTH_OR_HEIGHT_TOO_BIG)
    private String avatar;

}
