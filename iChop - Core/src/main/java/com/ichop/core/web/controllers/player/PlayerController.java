package com.ichop.core.web.controllers.player;

import com.google.gson.Gson;
import com.ichop.core.constants.URLConstants;
import com.ichop.core.domain.models.jms.key.returnn.PlayerDataByKeyJMSReturnModel;
import com.ichop.core.domain.models.json.IsPlayerAccountLinkedJSONModel;
import com.ichop.core.service.player.PlayerServices;
import com.ichop.core.web.controllers.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class PlayerController extends BaseController {

    private final PlayerServices playerServices;
    private final ModelMapper modelMapper;

    @Autowired
    public PlayerController(PlayerServices playerServices, ModelMapper modelMapper) {
        this.playerServices = playerServices;
        this.modelMapper = modelMapper;
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(URLConstants.PLAYER_LINK_ACCOUNT_GET)
    public ModelAndView playerLinkAccountGet(@RequestParam(value = "key") String key, ModelAndView modelAndView) {
        boolean isKeyValid = this.playerServices.isPlayerLinkKeyValid(key);

        if (!isKeyValid) {
            return super.viewWithMessage("notification/error", "Key Error", "Key is expired or not valid!");
        }

        PlayerDataByKeyJMSReturnModel result = this.playerServices.getPlayerDataByLinkKey(key);
        modelAndView.addObject("uuid", result.getPlayerUUID());
        modelAndView.addObject("name", result.getPlayerName());


        return super.page("player/player-link-account", "Link Account", modelAndView);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.PLAYER_LINK_ACCOUNT_POST)
    public ModelAndView playerLinkAccountPost(@RequestParam(value = "key") String key, ModelAndView modelAndView, Principal principal) {

        boolean isKeyValid = this.playerServices.isPlayerLinkKeyValid(key);

        if (!isKeyValid) {
            return super.viewWithMessage("notification/error", "Key Error", "Key is expired or not valid!");
        }

        boolean isLinkedAccount = this.playerServices.isPlayerLinkedAccountBySiteUser(principal.getName());

        if (isLinkedAccount) {
            return super.viewWithMessage("notification/error", "Link Error", "You already linked account to this account!");
        }

        PlayerDataByKeyJMSReturnModel playerDataByLinkKey = this.playerServices.getPlayerDataByLinkKey(key);
        this.playerServices.sendSiteUserToPlayerLinkConnection(playerDataByLinkKey.getPlayerName(), playerDataByLinkKey.getPlayerUUID(), principal.getName());

        return super.viewWithMessage("notification/info", "Successful!", "You have successful linked your account!");
    }

    @GetMapping(value = URLConstants.PLAYER_IS_PLAYER_LINKED_ACCOUNT_GET, produces = "application/json")
    @ResponseBody
    public String isPlayerLinkedAccount(@RequestParam(value = "uuid") String uuid) {
        IsPlayerAccountLinkedJSONModel isPlayerAccountLinkedJSONModel = new IsPlayerAccountLinkedJSONModel();
        isPlayerAccountLinkedJSONModel.setAccountLinked(this.playerServices.isPlayerLinkedAccountByUUID(uuid));
        return new Gson().toJson(isPlayerAccountLinkedJSONModel);
    }

}
