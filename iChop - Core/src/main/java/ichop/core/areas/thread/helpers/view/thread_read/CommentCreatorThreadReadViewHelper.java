package ichop.core.areas.thread.helpers.view.thread_read;

import ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import ichop.core.areas.comment.services.CommentServices;
import ichop.core.areas.player.domain.jms.key.receive.PlayerDataBySiteUserJMSReceiveModel;
import ichop.core.areas.player.services.PlayerLinkJmsServices;
import ichop.core.areas.thread.domain.models.view.thread_read.CommentCreatorThreadReadViewModel;
import ichop.core.base.BaseViewCreator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentCreatorThreadReadViewHelper extends BaseViewCreator {

    private final CommentServices commentServices;
    private final PlayerLinkJmsServices playerLinkJmsServices;

    @Autowired
    public CommentCreatorThreadReadViewHelper(ModelMapper modelMapper, CommentServices commentServices, PlayerLinkJmsServices playerLinkJmsServices) {
        super(modelMapper);
        this.commentServices = commentServices;
        this.playerLinkJmsServices = playerLinkJmsServices;
    }


    public CommentCreatorThreadReadViewModel create(String commentId) {
        CommentServiceModel commentServiceModel = this.commentServices.findById(commentId);

        int totalComments = this.commentServices.getTotalOfUser(commentServiceModel.getCreator());
        PlayerDataBySiteUserJMSReceiveModel playerData = this.playerLinkJmsServices.getPlayerDataBySiteUser(commentServiceModel.getCreator().getUsername());
        String minecraftAccountName = playerData != null ? playerData.getPlayerName() : null;
        String minecraftAccountUUID = playerData != null ? playerData.getPlayerUUID() : null;

        CommentCreatorThreadReadViewModel result = super.modelMapper.map(commentServiceModel.getCreator(), CommentCreatorThreadReadViewModel.class);
        result.setTotalComments(totalComments);
        result.setMinecraftAccountName(minecraftAccountName);
        result.setMinecraftAccountUUID(minecraftAccountUUID);

        return result;
    }

}
