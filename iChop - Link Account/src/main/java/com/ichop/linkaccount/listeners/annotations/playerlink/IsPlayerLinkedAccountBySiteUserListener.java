package com.ichop.linkaccount.listeners.annotations.playerlink;

import com.ichop.linkaccount.constants.PlayerLinkJMSConstants;
import org.springframework.jms.annotation.JmsListener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JmsListener(destination = PlayerLinkJMSConstants.IS_PLAYER_LINKED_ACCOUNT_BY_SITE_USER)
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsPlayerLinkedAccountBySiteUserListener {
}
