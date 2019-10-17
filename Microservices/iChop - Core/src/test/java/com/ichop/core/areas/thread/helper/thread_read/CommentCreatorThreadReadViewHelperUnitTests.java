package com.ichop.core.areas.thread.helper.thread_read;

import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.comment.services.CommentServices;
import com.ichop.core.areas.player.domain.jms.key.receive.PlayerDataBySiteUserJMSReceiveModel;
import com.ichop.core.areas.player.services.PlayerLinkJmsServices;
import com.ichop.core.areas.thread.domain.models.view.thread_read.CommentCreatorThreadReadViewModel;
import com.ichop.core.areas.thread.helpers.view.thread_read.CommentCreatorThreadReadViewHelper;
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
public class CommentCreatorThreadReadViewHelperUnitTests {

    @Mock
    private CommentServices commentServices;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PlayerLinkJmsServices playerLinkJmsServices;

    @Spy
    @InjectMocks
    private CommentCreatorThreadReadViewHelper commentCreatorThreadReadViewHelper;


    @Test
    public void create_withNotExistingPlayerData_shouldSetMinecraftParametersToNull() {
        String commentId = "id";
        CommentServiceModel commentServiceModel = mock(CommentServiceModel.class);
        UserServiceModel creator = mock(UserServiceModel.class);
        CommentCreatorThreadReadViewModel resultModel = mock(CommentCreatorThreadReadViewModel.class);

        when(commentServiceModel.getCreator()).thenReturn(creator);
        when(creator.getUsername()).thenReturn("username");
        when(this.commentServices.findById(commentId)).thenReturn(commentServiceModel);
        when(this.commentServices.getTotalOfUser(creator)).thenReturn(0);
        when(this.playerLinkJmsServices.getPlayerDataBySiteUser(creator.getUsername())).thenReturn(null);
        when(this.modelMapper.map(commentServiceModel.getCreator(), CommentCreatorThreadReadViewModel.class)).thenReturn(resultModel);

        this.commentCreatorThreadReadViewHelper.create(commentId);

        verify(resultModel, times(1)).setMinecraftAccountName(null);
        verify(resultModel, times(1)).setMinecraftAccountUUID(null);
    }

    @Test
    public void create_withValidData_shouldInvokeMethods() {
        String commentId = "id";
        CommentServiceModel commentServiceModel = mock(CommentServiceModel.class);
        UserServiceModel creator = mock(UserServiceModel.class);
        PlayerDataBySiteUserJMSReceiveModel playerData = mock(PlayerDataBySiteUserJMSReceiveModel.class);
        CommentCreatorThreadReadViewModel resultModel = mock(CommentCreatorThreadReadViewModel.class);

        when(commentServiceModel.getCreator()).thenReturn(creator);
        when(creator.getUsername()).thenReturn("username");
        when(this.commentServices.findById(commentId)).thenReturn(commentServiceModel);
        when(this.commentServices.getTotalOfUser(creator)).thenReturn(0);
        when(playerData.getPlayerName()).thenReturn("playerName");
        when(playerData.getPlayerUUID()).thenReturn("playerUUID");
        when(this.playerLinkJmsServices.getPlayerDataBySiteUser(creator.getUsername())).thenReturn(playerData);
        when(this.modelMapper.map(commentServiceModel.getCreator(), CommentCreatorThreadReadViewModel.class)).thenReturn(resultModel);

        this.commentCreatorThreadReadViewHelper.create(commentId);

        verify(resultModel, times(1)).setMinecraftAccountName(playerData.getPlayerName());
        verify(resultModel, times(1)).setMinecraftAccountUUID(playerData.getPlayerUUID());
        verify(resultModel, times(1)).setTotalComments(0);
        verify(this.commentServices, times(1)).findById(commentId);
        verify(this.commentServices, times(1)).getTotalOfUser(creator);
        verify(this.playerLinkJmsServices, times(1)).getPlayerDataBySiteUser(creator.getUsername());
        verify(this.modelMapper, times(1)).map(commentServiceModel.getCreator(), CommentCreatorThreadReadViewModel.class);
    }

}
