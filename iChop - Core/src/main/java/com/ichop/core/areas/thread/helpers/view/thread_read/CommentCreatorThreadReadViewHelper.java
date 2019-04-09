package com.ichop.core.areas.thread.helpers.view.thread_read;

import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.comment.services.CommentServices;
import com.ichop.core.areas.player.domain.jms.key.receive.PlayerDataBySiteUserJMSReceiveModel;
import com.ichop.core.areas.player.services.PlayerLinkServices;
import com.ichop.core.areas.thread.domain.models.view.thread_read.CommentCreatorThreadReadViewModel;
import com.ichop.core.base.BaseViewCreator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentCreatorThreadReadViewHelper extends BaseViewCreator {

    private final CommentServices commentServices;
    private final PlayerLinkServices playerLinkServices;

    @Autowired
    public CommentCreatorThreadReadViewHelper(ModelMapper modelMapper, CommentServices commentServices, PlayerLinkServices playerLinkServices) {
        super(modelMapper);
        this.commentServices = commentServices;
        this.playerLinkServices = playerLinkServices;
    }


    public CommentCreatorThreadReadViewModel create(String commentId) {
        CommentServiceModel commentServiceModel = this.commentServices.findById(commentId);

        int totalComments = this.commentServices.getTotalOfUser(commentServiceModel.getCreator());
        PlayerDataBySiteUserJMSReceiveModel playerData = this.playerLinkServices.getPlayerDataBySiteUser(commentServiceModel.getCreator().getUsername());
        String minecraftAccountName = playerData != null ? playerData.getPlayerName() : null;
        String minecraftAccountUUID = playerData != null ? playerData.getPlayerUUID() : null;

        CommentCreatorThreadReadViewModel result = super.modelMapper.map(commentServiceModel.getCreator(), CommentCreatorThreadReadViewModel.class);
        result.setTotalComments(totalComments);
        result.setMinecraftAccountName(minecraftAccountName);
        result.setMinecraftAccountUUID(minecraftAccountUUID);

        return result;
    }

}
