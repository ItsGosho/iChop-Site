package com.ichop.basicstatistics.domain.models.jms.receive;

import com.ichop.basicstatistics.constants.ValidationConstants;
import com.ichop.basicstatistics.constants.ValidationMessages;
import com.ichop.basicstatistics.validators.MustExistByUUID;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.ichop.basicstatistics.constants.ValidationConstants.*;
import static com.ichop.basicstatistics.constants.ValidationMessages.*;

@Getter
@Setter
public class GetPlayerBasicStatisticsByUUIDJmsReceiveModel {

    @NotNull
    @NotEmpty
    @Pattern(regexp = UUID_PATTERN
            ,message = UUID_NOT_VALID)
    @MustExistByUUID(message = NOT_USER_EXIST_BY_UUID)
    private String uuid;

}
