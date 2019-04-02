package com.ichop.core.areas.player.helpers.player_profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.core.areas.player.domain.jms.player.basicstatistics.receive.GetPlayerBasicStatisticsByUUIDJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.player.link.receive.GetPlayerDataByPlayerUUIDJMSReceiveModel;
import com.ichop.core.areas.player.domain.models.view.PlayerProfileViewModel;
import com.ichop.core.areas.player.services.PlayerBasicStatisticServices;
import com.ichop.core.areas.player.services.PlayerLinkServices;
import com.ichop.core.base.BaseViewCreator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerProfileViewCreator extends BaseViewCreator {

    private final PlayerBasicStatisticServices playerBasicStatisticServices;
    private final PlayerLinkServices playerLinkServices;
    private final ObjectMapper objectMapper;

    @Autowired
    protected PlayerProfileViewCreator(ModelMapper modelMapper, PlayerBasicStatisticServices playerBasicStatisticServices, PlayerLinkServices playerLinkServices, ObjectMapper objectMapper) {
        super(modelMapper);
        this.playerBasicStatisticServices = playerBasicStatisticServices;
        this.playerLinkServices = playerLinkServices;
        this.objectMapper = objectMapper;
    }


    public PlayerProfileViewModel create(String uuid) {

        GetPlayerBasicStatisticsByUUIDJMSReceiveModel foundedPlayer = this.playerBasicStatisticServices.getPlayerDataByUUID(uuid);

        if (foundedPlayer.hasErrors()) {
            return null;
        }

        PlayerProfileViewModel result = this.objectMapper.convertValue(foundedPlayer, PlayerProfileViewModel.class);
        GetPlayerDataByPlayerUUIDJMSReceiveModel foundedLink = this.playerLinkServices.getPlayerDataByPlayerUUID(result.getUuid());

        if (!foundedLink.hasErrors()) {
            GetPlayerDataByPlayerUUIDJMSReceiveModel link = this.objectMapper.convertValue(foundedLink, GetPlayerDataByPlayerUUIDJMSReceiveModel.class);
            result.setSiteUserUsername(link.getSiteUserUsername());
        }

        return result;
    }


}
