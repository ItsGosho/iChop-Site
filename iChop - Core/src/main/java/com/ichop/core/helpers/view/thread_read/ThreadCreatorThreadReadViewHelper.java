package com.ichop.core.helpers.view.thread_read;

import com.ichop.core.domain.models.jms.key.returnn.PlayerDataBySiteUserJMSReturnModel;
import com.ichop.core.domain.models.service.thread.ThreadServiceModel;
import com.ichop.core.domain.models.view.thread_read.ThreadCreatorThreadReadViewModel;
import com.ichop.core.helpers.view.BaseViewCreator;
import com.ichop.core.service.comment.CommentServices;
import com.ichop.core.service.player.link.PlayerLinkServices;
import com.ichop.core.service.thread.ThreadServices;
import com.ichop.core.service.user.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThreadCreatorThreadReadViewHelper extends BaseViewCreator {

    private final ThreadServices threadServices;
    private final CommentServices commentServices;
    private final PlayerLinkServices playerLinkServices;

    @Autowired
    public ThreadCreatorThreadReadViewHelper(ModelMapper modelMapper, ThreadServices threadServices, CommentServices commentServices, PlayerLinkServices playerLinkServices, UserServices userServices) {
        super(modelMapper);
        this.threadServices = threadServices;
        this.commentServices = commentServices;
        this.playerLinkServices = playerLinkServices;
    }

    public ThreadCreatorThreadReadViewModel create(String threadId) {
        ThreadServiceModel thread = this.threadServices.findThreadById(threadId);

        PlayerDataBySiteUserJMSReturnModel player = this.playerLinkServices.getPlayerDataBySiteUser(thread.getCreator().getUsername());
        String minecraftAccountName = player != null ? player.getPlayerName() : null;
        int totalComments = this.commentServices.getTotalCommentsOfUser(thread.getCreator());

        ThreadCreatorThreadReadViewModel result = super.modelMapper.map(thread.getCreator(),ThreadCreatorThreadReadViewModel.class);
        result.setMinecraftAccountName(minecraftAccountName);
        result.setTotalComments(totalComments);

        return result;
    }

}
