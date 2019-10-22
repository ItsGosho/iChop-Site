package com.ichop.core.areas.player.helpers.player_profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.core.areas.player.domain.jms.player.basicstatistics.receive.GetPlayerBasicStatisticsByUUIDJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.player.link.receive.GetPlayerDataByPlayerUUIDJMSReceiveModel;
import com.ichop.core.areas.player.domain.models.view.PlayerProfileViewModel;
import com.ichop.core.areas.player.services.PlayerBasicStatisticJmsServices;
import com.ichop.core.areas.player.services.PlayerLinkJmsServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerProfileViewHelperUnitTests {


    @Mock
    private PlayerBasicStatisticJmsServices playerBasicStatisticJmsServices;

    @Mock
    private PlayerLinkJmsServices playerLinkJmsServices;

    @Mock
    private ObjectMapper objectMapper;

    @Spy
    @InjectMocks
    private PlayerProfileViewHelper playerProfileViewHelper;


    @Test
    public void create_withErrorsInFoundedPlayer_shouldReturnNull() {
        String uuid = "uuid";
        GetPlayerBasicStatisticsByUUIDJMSReceiveModel foundedPlayer = mock(GetPlayerBasicStatisticsByUUIDJMSReceiveModel.class);

        when(this.playerBasicStatisticJmsServices.getPlayerDataByUUID(uuid)).thenReturn(foundedPlayer);
        when(foundedPlayer.hasErrors()).thenReturn(true);

        PlayerProfileViewModel result = this.playerProfileViewHelper.create(uuid);

        assertNull(result);
    }

    @Test
    public void create_withErrorsInLink_shouldNotInvokeMethodsAfterIf() {
        String uuid = "uuid";
        GetPlayerBasicStatisticsByUUIDJMSReceiveModel foundedPlayer = mock(GetPlayerBasicStatisticsByUUIDJMSReceiveModel.class);
        PlayerProfileViewModel playerProfileViewModel = mock(PlayerProfileViewModel.class);
        GetPlayerDataByPlayerUUIDJMSReceiveModel foundedLink = mock(GetPlayerDataByPlayerUUIDJMSReceiveModel.class);

        when(this.objectMapper.convertValue(foundedPlayer,PlayerProfileViewModel.class)).thenReturn(playerProfileViewModel);
        when(this.playerBasicStatisticJmsServices.getPlayerDataByUUID(uuid)).thenReturn(foundedPlayer);
        when(playerProfileViewModel.getUuid()).thenReturn(uuid);
        when(this.playerLinkJmsServices.getPlayerDataByPlayerUUID(uuid)).thenReturn(foundedLink);
        when(foundedLink.hasErrors()).thenReturn(true);
        when(foundedPlayer.hasErrors()).thenReturn(false);

        this.playerProfileViewHelper.create(uuid);

        verify(this.objectMapper,times(0)).convertValue(foundedLink,GetPlayerDataByPlayerUUIDJMSReceiveModel.class);
    }

    @Test
    public void create_ValidDate_shouldInvokeAllNecesseryMethods() {
        String uuid = "uuid";
        String siteUserUsername = "username";
        GetPlayerBasicStatisticsByUUIDJMSReceiveModel foundedPlayer = mock(GetPlayerBasicStatisticsByUUIDJMSReceiveModel.class);
        PlayerProfileViewModel playerProfileViewModel = mock(PlayerProfileViewModel.class);
        GetPlayerDataByPlayerUUIDJMSReceiveModel foundedLink = mock(GetPlayerDataByPlayerUUIDJMSReceiveModel.class);
        GetPlayerDataByPlayerUUIDJMSReceiveModel link = mock(GetPlayerDataByPlayerUUIDJMSReceiveModel.class);

        when(this.objectMapper.convertValue(foundedPlayer,PlayerProfileViewModel.class)).thenReturn(playerProfileViewModel);
        when(this.playerBasicStatisticJmsServices.getPlayerDataByUUID(uuid)).thenReturn(foundedPlayer);
        when(playerProfileViewModel.getUuid()).thenReturn(uuid);
        when(this.playerLinkJmsServices.getPlayerDataByPlayerUUID(uuid)).thenReturn(foundedLink);
        when(foundedLink.hasErrors()).thenReturn(false);
        when(foundedPlayer.hasErrors()).thenReturn(false);
        when(this.objectMapper.convertValue(foundedLink,GetPlayerDataByPlayerUUIDJMSReceiveModel.class)).thenReturn(link);
        when(link.getSiteUserUsername()).thenReturn(siteUserUsername);

        this.playerProfileViewHelper.create(uuid);

        verify(this.objectMapper,times(1)).convertValue(foundedPlayer,PlayerProfileViewModel.class);
        verify(this.playerBasicStatisticJmsServices,times(1)).getPlayerDataByUUID(uuid);
        verify(this.playerLinkJmsServices,times(1)).getPlayerDataByPlayerUUID(uuid);
        verify(this.objectMapper,times(1)).convertValue(foundedLink,GetPlayerDataByPlayerUUIDJMSReceiveModel.class);
    }

}
