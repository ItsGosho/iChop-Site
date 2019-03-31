package com.ichop.linkaccount.domain.models.jms.playerlink.receive;

import com.ichop.linkaccount.validators.ExistingSiteUserUsername;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class IsPlayerLinkedAccountBySiteUserJMSReceiveModel {

    @NotNull
    @NotEmpty
    private String siteUserUsername;

}
