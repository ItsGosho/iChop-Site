package com.ichop.core.web.controllers.player;

import com.google.gson.Gson;
import com.ichop.core.components.jms.JmsServices;
import com.ichop.core.constants.URLConstants;
import com.ichop.core.domain.models.json.IsPlayerAccountLinkedJSONModel;
import com.ichop.core.web.controllers.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;

@Controller
public class PlayerController extends BaseController {

    private final JmsServices jmsServices;
    private final ModelMapper modelMapper;

    public PlayerController(JmsServices jmsServices, ModelMapper modelMapper) {
        this.jmsServices = jmsServices;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(URLConstants.PLAYER_LINK_ACCOUNT_GET)
    public ModelAndView playerLinkAccountGet(@RequestParam(value = "key") String key, ModelAndView modelAndView){
        boolean isKeyValid = this.jmsServices.isPlayerLinkKeyValid(key);

        if(!isKeyValid){
            return super.viewWithMessage("notification/error","Key Error","Key is expired or not valid!");
        }

        Map result = this.jmsServices.getPlayerDataByLinkKey(key);
        modelAndView.addObject("uuid",result.get("uuid"));
        modelAndView.addObject("name",result.get("name"));


        return super.page("player/player-link-account","Link Account",modelAndView);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.PLAYER_LINK_ACCOUNT_POST)
    public ModelAndView playerLinkAccountPost(@RequestParam(value = "key") String key, ModelAndView modelAndView, Principal principal){

        boolean isKeyValid = this.jmsServices.isPlayerLinkKeyValid(key);

        if(!isKeyValid){
            return super.viewWithMessage("notification/error","Key Error","Key is expired or not valid!");
        }

        Map result = this.jmsServices.getPlayerDataByLinkKey(key);
        boolean isSuccessful = this.jmsServices.sendSiteUserToPlayerLinkConnection((String) result.get("name"),(String) result.get("uuid"),principal.getName());

        if(isSuccessful){
            return super.viewWithMessage("notification/info","Successful!","You have successful linked your account!");
        }

        return super.viewWithMessage("notification/error","Error!","A error occured while linking your account ,please try again!");
    }

    @GetMapping(value = URLConstants.PLAYER_IS_PLAYER_LINKED_ACCOUNT_GET,produces = "application/json")
    @ResponseBody
    public String isPlayerLinkedAccount(@RequestParam(value = "playerUUID") String playerUUID){
        IsPlayerAccountLinkedJSONModel isPlayerAccountLinkedJSONModel = new IsPlayerAccountLinkedJSONModel();
        isPlayerAccountLinkedJSONModel.setAccountLinked(false);
        return new Gson().toJson(isPlayerAccountLinkedJSONModel);
    }

}
