package com.ichop.basicstatistics.validators;

import com.ichop.basicstatistics.services.PlayerStatisticsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class MustExistByUUIDValidator implements ConstraintValidator<MustExistByUUID, String> {

    private final PlayerStatisticsServices playerStatisticsServices;

    @Autowired
    public MustExistByUUIDValidator(PlayerStatisticsServices playerStatisticsServices) {
        this.playerStatisticsServices = playerStatisticsServices;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.playerStatisticsServices.existsByUUID(value);
    }
}
