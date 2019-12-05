package com.ichop.linkaccount.domain.models.jms.key.receive;

import com.ichop.linkaccount.validators.ExistingSiteUserUsername;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GetPlayerDataBySiteUserJMSReceiveModel {

    @NotNull
    @NotEmpty
    @ExistingSiteUserUsername
    private String siteUserUsername;
}
