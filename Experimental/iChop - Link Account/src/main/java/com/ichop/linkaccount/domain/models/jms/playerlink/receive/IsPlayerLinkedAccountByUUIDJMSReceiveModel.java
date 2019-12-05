package com.ichop.linkaccount.domain.models.jms.playerlink.receive;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.ichop.linkaccount.constants.ValidationConstants.UUID_PATTERN;
import static com.ichop.linkaccount.constants.ValidationMessages.UUID_NOT_VALID;

@Getter
@Setter
public class IsPlayerLinkedAccountByUUIDJMSReceiveModel {

    @NotNull
    @NotEmpty
    @Pattern(regexp = UUID_PATTERN
            ,message = UUID_NOT_VALID)
    private String uuid;

}
