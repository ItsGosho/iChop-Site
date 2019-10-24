package ichop.core.areas.player.helpers.player_profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.core.areas.jms.exception.JmsServerIsDownException;
import ichop.core.areas.player.domain.jms.player.basicstatistics.receive.GetPlayerBasicStatisticsByUUIDJMSReceiveModel;
import ichop.core.areas.player.domain.jms.player.link.receive.GetPlayerDataByPlayerUUIDJMSReceiveModel;
import ichop.core.areas.player.domain.models.view.PlayerProfileViewModel;
import ichop.core.areas.player.services.PlayerBasicStatisticJmsServices;
import ichop.core.areas.player.services.PlayerLinkJmsServices;
import ichop.core.base.BaseViewCreator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerProfileViewHelper extends BaseViewCreator {

    private final PlayerBasicStatisticJmsServices playerBasicStatisticJmsServices;
    private final PlayerLinkJmsServices playerLinkJmsServices;
    private final ObjectMapper objectMapper;

    @Autowired
    protected PlayerProfileViewHelper(ModelMapper modelMapper, PlayerBasicStatisticJmsServices playerBasicStatisticJmsServices, PlayerLinkJmsServices playerLinkJmsServices, ObjectMapper objectMapper) {
        super(modelMapper);
        this.playerBasicStatisticJmsServices = playerBasicStatisticJmsServices;
        this.playerLinkJmsServices = playerLinkJmsServices;
        this.objectMapper = objectMapper;
    }


    public PlayerProfileViewModel create(String uuid) {

        GetPlayerBasicStatisticsByUUIDJMSReceiveModel foundedPlayer = this.playerBasicStatisticJmsServices.getPlayerDataByUUID(uuid);

        if(foundedPlayer == null){
            throw new JmsServerIsDownException();
        }

        if (foundedPlayer.hasErrors()) {
            return null;
        }

        PlayerProfileViewModel result = this.objectMapper.convertValue(foundedPlayer, PlayerProfileViewModel.class);
        GetPlayerDataByPlayerUUIDJMSReceiveModel foundedLink = this.playerLinkJmsServices.getPlayerDataByPlayerUUID(result.getUuid());

        if (!foundedLink.hasErrors()) {
            GetPlayerDataByPlayerUUIDJMSReceiveModel link = this.objectMapper.convertValue(foundedLink, GetPlayerDataByPlayerUUIDJMSReceiveModel.class);
            result.setSiteUserUsername(link.getSiteUserUsername());
        }

        return result;
    }


}
