package com.ichop.core.areas.user.domain.models.jms;

import com.ichop.core.base.BaseJMSSendModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateAvatarJmsSendModel extends BaseJMSSendModel {

    private String username;
    private String avatar;

}
