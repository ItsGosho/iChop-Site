package com.ichop.core.areas.thread.helper.thread_read;

import com.ichop.core.areas.comment.services.CommentServices;
import com.ichop.core.areas.player.domain.jms.key.receive.PlayerDataBySiteUserJMSReceiveModel;
import com.ichop.core.areas.player.services.PlayerLinkJmsServices;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.domain.models.view.thread_read.ThreadCreatorThreadReadViewModel;
import com.ichop.core.areas.thread.helpers.view.thread_read.ThreadCreatorThreadReadViewHelper;
import com.ichop.core.areas.thread.services.ThreadServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ThreadCreatorThreadReadViewHelperUnitTests {

    @Mock
    private ThreadServices threadServices;

    @Mock
    private CommentServices commentServices;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PlayerLinkJmsServices playerLinkJmsServices;

    @Spy
    @InjectMocks
    private ThreadCreatorThreadReadViewHelper threadCreatorThreadReadViewHelper;


    @Test
    public void create_withNotExistingPlayerData_shouldSetMinecraftParametersToNull() {
        String threadId = "id";
        ThreadServiceModel threadServiceModel = mock(ThreadServiceModel.class);
        UserServiceModel creator = mock(UserServiceModel.class);
        ThreadCreatorThreadReadViewModel resultModel = mock(ThreadCreatorThreadReadViewModel.class);

        when(threadServiceModel.getCreator()).thenReturn(creator);
        when(creator.getUsername()).thenReturn("username");
        when(this.threadServices.findById(threadId)).thenReturn(threadServiceModel);
        when(this.playerLinkJmsServices.getPlayerDataBySiteUser(creator.getUsername())).thenReturn(null);
        when(this.commentServices.getTotalOfUser(creator)).thenReturn(0);
        when(this.modelMapper.map(threadServiceModel.getCreator(), ThreadCreatorThreadReadViewModel.class)).thenReturn(resultModel);

        this.threadCreatorThreadReadViewHelper.create(threadId);

        verify(resultModel, times(1)).setMinecraftAccountName(null);
        verify(resultModel, times(1)).setMinecraftAccountUUID(null);
        verify(resultModel, times(1)).setTotalComments(0);
    }

    @Test
    public void create_withValidData_shouldInvokeMethods() {
        String threadId = "id";
        ThreadServiceModel threadServiceModel = mock(ThreadServiceModel.class);
        UserServiceModel creator = mock(UserServiceModel.class);
        PlayerDataBySiteUserJMSReceiveModel playerData = mock(PlayerDataBySiteUserJMSReceiveModel.class);
        ThreadCreatorThreadReadViewModel resultModel = mock(ThreadCreatorThreadReadViewModel.class);

        when(threadServiceModel.getCreator()).thenReturn(creator);
        when(creator.getUsername()).thenReturn("username");
        when(this.threadServices.findById(threadId)).thenReturn(threadServiceModel);
        when(this.commentServices.getTotalOfUser(creator)).thenReturn(0);
        when(playerData.getPlayerName()).thenReturn("playerName");
        when(playerData.getPlayerUUID()).thenReturn("playerUUID");
        when(this.playerLinkJmsServices.getPlayerDataBySiteUser(creator.getUsername())).thenReturn(playerData);
        when(this.modelMapper.map(threadServiceModel.getCreator(), ThreadCreatorThreadReadViewModel.class)).thenReturn(resultModel);

        this.threadCreatorThreadReadViewHelper.create(threadId);

        verify(resultModel, times(1)).setMinecraftAccountName(playerData.getPlayerName());
        verify(resultModel, times(1)).setMinecraftAccountUUID(playerData.getPlayerUUID());
        verify(resultModel, times(1)).setTotalComments(0);
        verify(this.threadServices, times(1)).findById(threadId);
        verify(this.commentServices, times(1)).getTotalOfUser(creator);
        verify(this.playerLinkJmsServices, times(1)).getPlayerDataBySiteUser(creator.getUsername());
        verify(this.modelMapper, times(1)).map(threadServiceModel.getCreator(), ThreadCreatorThreadReadViewModel.class);
    }

}
