package com.ichop.linkaccount.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.linkaccount.domain.entities.PlayerLink;
import com.ichop.linkaccount.domain.models.binding.PlayerLinkCreateBindingModel;
import com.ichop.linkaccount.domain.models.service.PlayerLinkServiceModel;
import com.ichop.linkaccount.repositories.KeyRepository;
import com.ichop.linkaccount.repositories.PlayerLinkRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerLinkServicesUnitTests {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private PlayerLinkRepository playerLinkRepository;

    @Mock
    private KeyServices keyServices;


    @Spy
    @InjectMocks
    private PlayerLinkServicesImp playerLinkServices;


    @Before
    public void setUp() {

    }

    @Test
    public void linkToSiteUser_withAlreadyLinkedAccount_shouldReturnFalse() {
        PlayerLinkCreateBindingModel bindingModel = mock(PlayerLinkCreateBindingModel.class);
        PlayerLinkServiceModel playerLink = mock(PlayerLinkServiceModel.class);
        String playerUUID = "uuid";

        when(playerLink.getPlayerUUID()).thenReturn(playerUUID);
        when(this.objectMapper.convertValue(bindingModel, PlayerLinkServiceModel.class)).thenReturn(playerLink);
        doReturn(true).when(this.playerLinkServices).isAccountLinkedByUUID(playerUUID);

        boolean result = this.playerLinkServices.linkToSiteUser(bindingModel);

        assertFalse(result);
    }

    @Test
    public void linkToSiteUser_withValidData_shouldInvokeMethods() {
        PlayerLinkCreateBindingModel bindingModel = mock(PlayerLinkCreateBindingModel.class);
        PlayerLinkServiceModel playerLink = mock(PlayerLinkServiceModel.class);
        String playerUUID = "uuid";

        when(playerLink.getPlayerUUID()).thenReturn(playerUUID);
        when(this.objectMapper.convertValue(bindingModel, PlayerLinkServiceModel.class)).thenReturn(playerLink);
        doReturn(false).when(this.playerLinkServices).isAccountLinkedByUUID(playerUUID);
        doReturn(null).when(this.playerLinkServices).save(playerLink, PlayerLinkServiceModel.class);

        boolean result = this.playerLinkServices.linkToSiteUser(bindingModel);

        assertTrue(result);
        verify(this.playerLinkServices, times(1)).isAccountLinkedByUUID(playerUUID);
        verify(this.playerLinkServices, times(1)).save(playerLink, PlayerLinkServiceModel.class);
        verify(this.keyServices, times(1)).deleteByUUID(playerUUID);
    }

    @Test
    public void unlinkFromSiteUser_withNotLinkedAccount_shouldReturnFalse() {
        PlayerLinkServiceModel playerLink = mock(PlayerLinkServiceModel.class);
        String playerUUID = "uuid";

        when(playerLink.getPlayerUUID()).thenReturn(playerUUID);
        doReturn(false).when(this.playerLinkServices).isAccountLinkedByUUID(playerUUID);
        boolean result = this.playerLinkServices.unlinkFromSiteUser(playerLink);

        assertFalse(result);
    }

    @Test
    public void unlinkFromSiteUser_withValidData_shouldInvokeMethods() {
        PlayerLinkServiceModel playerLink = mock(PlayerLinkServiceModel.class);
        String playerUUID = "uuid";

        when(playerLink.getPlayerUUID()).thenReturn(playerUUID);
        doReturn(true).when(this.playerLinkServices).isAccountLinkedByUUID(playerUUID);
        doNothing().when(this.playerLinkServices).delete(playerLink);
        boolean result = this.playerLinkServices.unlinkFromSiteUser(playerLink);

        assertTrue(result);
        verify(this.playerLinkServices, times(1)).delete(playerLink);
    }

    @Test
    public void isPlayerLinkExistBySiteUser_withValidData_shouldInvokeMethods() {
        String username = "username";

        this.playerLinkServices.isPlayerLinkExistBySiteUser(username);

        verify(this.playerLinkRepository,times(1)).existsBySiteUserUsername(username);
    }

    @Test
    public void getBySiteUser_withValidData_shouldInvokeMethods() {
        String username = "username";
        PlayerLink playerLink = mock(PlayerLink.class);

        when(this.playerLinkRepository.getBySiteUserUsername(username)).thenReturn(playerLink);
        this.playerLinkServices.getBySiteUser(username);

        verify(this.playerLinkRepository,times(1)).getBySiteUserUsername(username);
        verify(this.objectMapper,times(1)).convertValue(playerLink,PlayerLinkServiceModel.class);
    }

    @Test
    public void getByUUID_withValidData_shouldInvokeMethods() {
        String uuid = "uuid";
        PlayerLink playerLink = mock(PlayerLink.class);

        when(this.playerLinkRepository.getByPlayerUUID(uuid)).thenReturn(playerLink);
        this.playerLinkServices.getByUUID(uuid);

        verify(this.playerLinkRepository,times(1)).getByPlayerUUID(uuid);
        verify(this.objectMapper,times(1)).convertValue(playerLink,PlayerLinkServiceModel.class);
    }

    @Test
    public void isAccountLinkedByUUID_withLinkedAccount_shouldReturnTrue() {
        String uuid = "uuid";
        PlayerLink playerLink = mock(PlayerLink.class);

        when(this.playerLinkRepository.getByPlayerUUID(uuid)).thenReturn(playerLink);
        boolean result = this.playerLinkServices.isAccountLinkedByUUID(uuid);

        assertTrue(result);
    }

    @Test
    public void isAccountLinkedByUUID_withNotLinkedAccount_shouldReturnFalse() {
        String uuid = "uuid";

        when(this.playerLinkRepository.getByPlayerUUID(uuid)).thenReturn(null);
        boolean result = this.playerLinkServices.isAccountLinkedByUUID(uuid);

        assertFalse(result);
    }

    @Test
    public void isAccountLinkedBySiteUserUsername_withLinkedAccount_shouldReturnTrue() {
        String username = "username";
        PlayerLink playerLink = mock(PlayerLink.class);

        when(this.playerLinkRepository.getBySiteUserUsername(username)).thenReturn(playerLink);
        boolean result = this.playerLinkServices.isAccountLinkedBySiteUserUsername(username);

        assertTrue(result);
    }

    @Test
    public void isAccountLinkedBySiteUserUsername_withNotLinkedAccount_shouldReturnFalse() {
        String username = "username";

        when(this.playerLinkRepository.getBySiteUserUsername(username)).thenReturn(null);
        boolean result = this.playerLinkServices.isAccountLinkedBySiteUserUsername(username);

        assertFalse(result);
    }

}