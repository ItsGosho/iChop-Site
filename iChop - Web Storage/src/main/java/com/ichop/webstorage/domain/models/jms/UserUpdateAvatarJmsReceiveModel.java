package com.ichop.webstorage.domain.models.jms;

import com.ichop.webstorage.validators.Base64;
import com.ichop.webstorage.validators.Base64Image;
import com.ichop.webstorage.validators.Base64Size;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.ichop.webstorage.constants.ValidationMessages.*;

@Getter
@Setter
public class UserUpdateAvatarJmsReceiveModel {

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
