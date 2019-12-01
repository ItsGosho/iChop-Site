package com.ichop.linkaccount.listeners.annotations.key;

import com.ichop.linkaccount.constants.KeyJMSConstants;
import org.springframework.jms.annotation.JmsListener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JmsListener(destination = KeyJMSConstants.GET_PLAYER_DATA_BY_KEY)
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GetPlayerDataByKeyListener {
}
