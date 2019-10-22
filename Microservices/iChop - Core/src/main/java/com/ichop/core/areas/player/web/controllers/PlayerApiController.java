package com.ichop.core.areas.player.web.controllers;

import com.google.gson.Gson;
import com.ichop.core.areas.jms.exception.JmsServerIsDownException;
import com.ichop.core.areas.player.domain.jms.player.link.receive.IsPlayerLinkedAccountByUUIDJMSReceiveModel;
import com.ichop.core.areas.player.domain.json.IsPlayerAccountLinkedJSONModel;
import com.ichop.core.areas.player.services.PlayerLinkJmsServices;
import com.ichop.core.constants.URLConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerApiController {

    private final PlayerLinkJmsServices playerLinkJmsServices;
    private final ModelMapper modelMapper;

    @Autowired
    public PlayerApiController(PlayerLinkJmsServices playerLinkJmsServices, ModelMapper modelMapper) {
        this.playerLinkJmsServices = playerLinkJmsServices;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = URLConstants.PLAYER_IS_PLAYER_LINKED_ACCOUNT_GET, produces = "application/json")
    @ResponseBody
    public String isPlayerLinkedAccount(@RequestParam(value = "uuid") String uuid) {
        IsPlayerLinkedAccountByUUIDJMSReceiveModel result = this.playerLinkJmsServices.isPlayerLinkedAccountByUUID(uuid);

        if(result == null){
            throw new JmsServerIsDownException();
        }

        IsPlayerAccountLinkedJSONModel isPlayerAccountLinkedJSONModel = this.modelMapper.map(result,IsPlayerAccountLinkedJSONModel.class);
        return new Gson().toJson(isPlayerAccountLinkedJSONModel);
    }

}
