package com.ichop.core.areas.player.web.controllers;

import com.ichop.core.areas.player.domain.jms.key.receive.PlayerDataByKeyJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.player.link.receive.LinkPlayerAccountJMSReceiveModel;
import com.ichop.core.areas.player.domain.models.view.PlayerProfileViewModel;
import com.ichop.core.areas.player.helpers.player_profile.PlayerProfileViewHelper;
import com.ichop.core.areas.player.services.PlayerLinkJmsServices;
import com.ichop.core.base.BaseController;
import com.ichop.core.constants.URLConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class PlayerController extends BaseController {

    private final PlayerLinkJmsServices playerLinkJmsServices;
    private final ModelMapper modelMapper;
    private final PlayerProfileViewHelper playerProfileViewHelper;

    @Autowired
    public PlayerController(PlayerLinkJmsServices playerLinkJmsServices, ModelMapper modelMapper, PlayerProfileViewHelper playerProfileViewHelper) {
        this.playerLinkJmsServices = playerLinkJmsServices;
        this.modelMapper = modelMapper;
        this.playerProfileViewHelper = playerProfileViewHelper;
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(URLConstants.PLAYER_LINK_ACCOUNT_GET)
    public ModelAndView getLinkAccountPage(@RequestParam(value = "key") String key, ModelAndView modelAndView) {
        PlayerDataByKeyJMSReceiveModel result = this.playerLinkJmsServices.getPlayerDataByLinkKey(key);

        if (result.hasErrors()) {
            return super.viewWithMessages("notification/errors", "Key Error", result.getErrors());
        }

        PlayerDataByKeyJMSReceiveModel resultModel = this.modelMapper.map(result, PlayerDataByKeyJMSReceiveModel.class);
        modelAndView.addObject("uuid", resultModel.getPlayerUUID());
        modelAndView.addObject("name", resultModel.getPlayerName());


        return super.page("player/player-link-account", "Link Account", modelAndView);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.PLAYER_LINK_ACCOUNT_POST)
    public ModelAndView proceedPlayerAccountLink(@RequestParam(value = "key") String key, Principal principal) {
        LinkPlayerAccountJMSReceiveModel result = this.playerLinkJmsServices.sendSiteUserToPlayerLinkConnection(key, principal.getName());

        if (result.hasErrors()) {
            return super.viewWithMessages("notification/errors", "Key Error", result.getErrors());
        }

        return super.viewWithMessage("notification/info", "Successful!", "You have successful linked your account!");
    }

    @GetMapping(URLConstants.PLAYER_PROFILE_VIEW_GET)
    public ModelAndView getPlayerProfileViewPage(@PathVariable String uuid,ModelAndView modelAndView) {
        PlayerProfileViewModel player = this.playerProfileViewHelper.create(uuid);

        if(player == null){
            return super.viewWithMessage("notification/error", "Player Error", "Player not found!");
        }

        modelAndView.addObject("player",player);

        return super.page("player/player-profile",String.format("%s`s profile",player.getName()),modelAndView);
    }


}
