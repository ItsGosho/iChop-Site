package com.ichop.core.web.controllers.player;

import com.google.gson.Gson;
import com.ichop.core.components.jms.JmsServices;
import com.ichop.core.constants.URLConstants;
import com.ichop.core.domain.models.json.IsPlayerAccountLinkedJSONModel;
import com.ichop.core.domain.models.view.link_account.LinkAccountViewModel;
import com.ichop.core.web.controllers.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class PlayerController extends BaseController {

    private final JmsServices jmsServices;
    private final ModelMapper modelMapper;

    public PlayerController(JmsServices jmsServices, ModelMapper modelMapper) {
        this.jmsServices = jmsServices;
        this.modelMapper = modelMapper;
    }

    @GetMapping(URLConstants.PLAYER_LINK_ACCOUNT_GET)
    public ModelAndView playerLinkAccountGet(@RequestParam(value = "key") String key){
        boolean isKeyValid = this.jmsServices.isPlayerLinkKeyValid(key);

        if(!isKeyValid){
            return super.viewWithMessage("notification/error","Key Error","Key is expired or not valid!");
        }

        this.modelMapper.getConfiguration().setFullTypeMatchingRequired(false);

        //TODO: ne iska da gi mapva :(((
        Map<String,Object> rez = this.jmsServices.getPlayerUUIDByLinkKey(key);
        LinkAccountViewModel result = this.modelMapper.map(rez,LinkAccountViewModel.class);

        return super.page("","");
    }

    @GetMapping(value = URLConstants.PLAYER_IS_PLAYER_LINKED_ACCOUNT_GET,produces = "application/json")
    @ResponseBody
    public String isPlayerLinkedAccount(@RequestParam(value = "playerUUID") String playerUUID){
        IsPlayerAccountLinkedJSONModel isPlayerAccountLinkedJSONModel = new IsPlayerAccountLinkedJSONModel();
        isPlayerAccountLinkedJSONModel.setAccountLinked(false);
        return new Gson().toJson(isPlayerAccountLinkedJSONModel);
    }

}
