package ichop.core.areas.comment.requester;

import ichop.core.areas.comment.models.jms.all.UserProfileCommentsByUserProfileUsernameRequest;
import ichop.core.areas.comment.models.jms.create.UserProfileCommentCreateRequest;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProfileCommentRequesterImp implements UserProfileCommentRequester {

    private final JmsHelper jmsHelper;

    private final String createDestination;
    private final String deleteByIdDestination;
    private final String findByUserProfileUsernameDestination;

    @Autowired
    public UserProfileCommentRequesterImp(JmsHelper jmsHelper,
                                          @Value("${artemis.queue.comments.user.profile.create}") String createDestination,
                                          @Value("${artemis.queue.comments.user.profile.delete.by.id}") String deleteByIdDestination,
                                          @Value("${artemis.queue.comments.user.profile.find.by.userProfileUsername}") String findByUserProfileUsernameDestination) {

        this.jmsHelper = jmsHelper;

        this.createDestination = createDestination;
        this.deleteByIdDestination = deleteByIdDestination;
        this.findByUserProfileUsernameDestination = findByUserProfileUsernameDestination;
    }


    @Override
    public JmsReplyModel create(UserProfileCommentCreateRequest request) {
        return null;
    }

    @Override
    public JmsReplyModel deleteById(String id) {
        return null;
    }

    @Override
    public JmsReplyModel findByUserProfileUsername(UserProfileCommentsByUserProfileUsernameRequest request) {
        return null;
    }
}
