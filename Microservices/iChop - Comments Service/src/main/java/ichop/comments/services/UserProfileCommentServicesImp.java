package ichop.comments.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.comments.domain.entities.ThreadComment;
import ichop.comments.domain.entities.UserProfileComment;
import ichop.comments.domain.models.service.ThreadCommentServiceModel;
import ichop.comments.domain.models.service.UserProfileCommentServiceModel;
import ichop.comments.repositories.ThreadCommentRepository;
import ichop.comments.repositories.UserProfileCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileCommentServicesImp extends AbstractCommentServices<UserProfileComment, UserProfileCommentServiceModel, UserProfileCommentRepository> implements UserProfileCommentServices {

    @Autowired
    public UserProfileCommentServicesImp(ObjectMapper objectMapper, UserProfileCommentRepository repository) {
        super(objectMapper, repository);
    }


}
