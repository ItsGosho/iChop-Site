package com.ichop.core.areas.player.aspects;

import com.ichop.core.areas.jms.exception.JmsServerIsDownException;
import com.ichop.core.areas.player.domain.jms.key.receive.PlayerDataBySiteUserJMSReceiveModel;
import com.ichop.core.areas.player.exceptions.AccountAlreadyLinkedException;
import com.ichop.core.areas.player.services.PlayerLinkJmsServices;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Aspect
@Component
public class IsPlayerAccountLinkedAroundProceeder {

    private final PlayerLinkJmsServices playerLinkJmsServices;

    @Autowired
    public IsPlayerAccountLinkedAroundProceeder(PlayerLinkJmsServices playerLinkJmsServices) {
        this.playerLinkJmsServices = playerLinkJmsServices;
    }

    @Around("@annotation(com.ichop.core.areas.player.aspects.IsPlayerAccountLinked)")
    public Object isPlayerAccountLinked(ProceedingJoinPoint joinPoint) throws Throwable {

        Principal principal = this.getPrincipal(joinPoint, 2);
        PlayerDataBySiteUserJMSReceiveModel playerData = this.getPlayerLinkedData(principal.getName());

        if(playerData == null){
            throw new JmsServerIsDownException();
        }

        if(!playerData.hasErrors()){
            throw new AccountAlreadyLinkedException();
        }

        return joinPoint.proceed();
    }

    private Principal getPrincipal(ProceedingJoinPoint proceedingJoinPoint, Integer positionInArgs) {
        Object[] methodPassedValues = proceedingJoinPoint.getArgs();
        Principal principal = (UsernamePasswordAuthenticationToken) methodPassedValues[positionInArgs];

        return principal;
    }

    private PlayerDataBySiteUserJMSReceiveModel getPlayerLinkedData(String userUsername) {
        PlayerDataBySiteUserJMSReceiveModel playerData = this.playerLinkJmsServices.getPlayerDataBySiteUser(userUsername);
        return playerData;
    }

}
