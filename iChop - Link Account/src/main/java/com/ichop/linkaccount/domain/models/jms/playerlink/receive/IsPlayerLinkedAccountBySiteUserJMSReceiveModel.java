package com.ichop.linkaccount.domain.models.jms.playerlink.receive;

import com.ichop.linkaccount.validators.ExistingSiteUserUsername;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.ichop.linkaccount.constants.ValidationConstants.UUID_PATTERN;
import static com.ichop.linkaccount.constants.ValidationMessages.UUID_NOT_VALID;

@Getter
@Setter
public class IsPlayerLinkedAccountBySiteUserJMSReceiveModel {

    @NotNull
    @NotEmpty
    private String siteUserUsername;

}
