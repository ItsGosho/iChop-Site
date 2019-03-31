package com.ichop.basicstatistics.listeners.annotations;

import com.ichop.basicstatistics.constants.PlayerStatisticsJMSConstants;
import org.springframework.jms.annotation.JmsListener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JmsListener(destination = PlayerStatisticsJMSConstants.GET_PLAYER_DATA_BY_UUID)
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GetPlayerDataByUUIDListener {
}
