package com.ichop.core.areas.thread.helpers.view.thread_read;

import com.ichop.core.areas.comment.services.CommentServices;
import com.ichop.core.areas.player.domain.jms.key.receive.PlayerDataBySiteUserJMSReceiveModel;
import com.ichop.core.areas.player.services.PlayerLinkServices;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.domain.models.view.thread_read.ThreadCreatorThreadReadViewModel;
import com.ichop.core.areas.thread.services.ThreadServices;
import com.ichop.core.base.BaseViewCreator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThreadCreatorThreadReadViewHelper extends BaseViewCreator {

    private final ThreadServices threadServices;
    private final CommentServices commentServices;
    private final PlayerLinkServices playerLinkServices;

    @Autowired
    public ThreadCreatorThreadReadViewHelper(ModelMapper modelMapper, ThreadServices threadServices, CommentServices commentServices, PlayerLinkServices playerLinkServices) {
        super(modelMapper);
        this.threadServices = threadServices;
        this.commentServices = commentServices;
        this.playerLinkServices = playerLinkServices;
    }


    public ThreadCreatorThreadReadViewModel create(String threadId) {
        ThreadServiceModel thread = this.threadServices.findById(threadId);

        PlayerDataBySiteUserJMSReceiveModel playerData = this.playerLinkServices.getPlayerDataBySiteUser(thread.getCreator().getUsername());

        String minecraftAccountName = playerData!= null ? playerData.getPlayerName() : null;
        String minecraftAccountUUID = playerData!= null ? playerData.getPlayerUUID() : null;
        int totalComments = this.commentServices.getTotalOfUser(thread.getCreator());

        ThreadCreatorThreadReadViewModel result = super.modelMapper.map(thread.getCreator(),ThreadCreatorThreadReadViewModel.class);
        result.setMinecraftAccountName(minecraftAccountName);
        result.setMinecraftAccountUUID(minecraftAccountUUID);
        result.setTotalComments(totalComments);

        return result;
    }

}
