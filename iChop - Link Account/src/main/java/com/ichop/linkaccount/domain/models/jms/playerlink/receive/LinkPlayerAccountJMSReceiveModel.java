package com.ichop.linkaccount.domain.models.jms.playerlink.receive;

import com.ichop.linkaccount.validators.NotLinkedBySiteUser;
import com.ichop.linkaccount.validators.NotLinkedByUUIDKey;
import com.ichop.linkaccount.validators.ValidKey;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LinkPlayerAccountJMSReceiveModel {

    @NotEmpty
    @NotNull
    @ValidKey
    @NotLinkedByUUIDKey
    private String key;

    @NotEmpty
    @NotNull
    @NotLinkedBySiteUser
    private String siteUserUsername;

}
