package com.ichop.linkaccount.domain.models.jms.key.receive;

import com.ichop.linkaccount.constants.ValidationMessages;
import com.ichop.linkaccount.validators.ValidKey;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GetPlayerDataByKeyJMSReceiveModel {

    @NotNull
    @NotEmpty
    @Length(min = 5,max = 5,message = ValidationMessages.KEY_LENGTH_WASNT_IN_RANGE)
    @ValidKey(message = ValidationMessages.KEY_NOT_VALID)
    private String key;

}
