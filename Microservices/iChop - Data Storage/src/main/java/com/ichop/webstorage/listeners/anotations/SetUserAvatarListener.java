package com.ichop.webstorage.listeners.anotations;

import com.ichop.webstorage.constants.UserDataJMSConstants;
import org.springframework.jms.annotation.JmsListener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JmsListener(destination = UserDataJMSConstants.SET_USER_AVATAR)
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SetUserAvatarListener {
}
