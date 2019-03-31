package com.ichop.linkaccount.domain.models.jms.playerlink.receive;

import com.ichop.linkaccount.constants.ValidationConstants;
import com.ichop.linkaccount.constants.ValidationMessages;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.ichop.linkaccount.constants.ValidationConstants.*;
import static com.ichop.linkaccount.constants.ValidationMessages.*;

@Getter
@Setter
public class LinkPlayerAccountJMSReceiveModel {

    @NotEmpty
    @NotNull
    private String playerName;

    @NotEmpty
    @NotNull
    @Pattern(regexp = UUID_PATTERN
            ,message = UUID_NOT_VALID)
    private String playerUUID;

    @NotEmpty
    @NotNull
    private String siteUserUsername;

}
