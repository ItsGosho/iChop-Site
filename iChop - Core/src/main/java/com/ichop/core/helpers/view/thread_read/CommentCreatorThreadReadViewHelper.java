package com.ichop.core.helpers.view.thread_read;

import com.ichop.core.domain.models.jms.key.returnn.PlayerDataBySiteUserJMSReturnModel;
import com.ichop.core.domain.models.service.comment.CommentServiceModel;
import com.ichop.core.domain.models.view.thread_read.CommentCreatorThreadReadViewModel;
import com.ichop.core.helpers.view.BaseViewCreator;
import com.ichop.core.service.comment.CommentServices;
import com.ichop.core.service.player.link.PlayerLinkServices;
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


    public CommentCreatorThreadReadViewModel create(String commentId){
        CommentServiceModel commentServiceModel = this.commentServices.findCommentById(commentId);

        int totalComments = this.commentServices.getTotalCommentsOfUser(commentServiceModel.getCreator());
        PlayerDataBySiteUserJMSReturnModel player = this.playerLinkServices.getPlayerDataBySiteUser(commentServiceModel.getCreator().getUsername());
        String minecraftAccountName = player!= null ? player.getPlayerName() : null;

        CommentCreatorThreadReadViewModel result = super.modelMapper.map(commentServiceModel.getCreator(),CommentCreatorThreadReadViewModel.class);
        result.setTotalComments(totalComments);
        result.setMinecraftAccountName(minecraftAccountName);

        return result;
    }

}
