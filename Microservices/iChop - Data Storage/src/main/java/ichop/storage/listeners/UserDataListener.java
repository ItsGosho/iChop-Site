package ichop.storage.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.storage.common.aop.JmsValidate;
import ichop.storage.common.helpers.BaseListener;
import ichop.storage.common.helpers.JmsHelper;
import ichop.storage.models.UserSetAvatarRequestModel;
import ichop.storage.services.UserDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.storage.common.constants.JmsFactories.QUEUE;

@Component
public class UserDataListener extends BaseListener {

    private final UserDataServices userDataServices;

    @Autowired
    protected UserDataListener(JmsHelper jmsHelper, ObjectMapper objectMapper, UserDataServices userDataServices) {
        super(jmsHelper, objectMapper);
        this.userDataServices = userDataServices;
    }

    @JmsValidate(model = UserSetAvatarRequestModel.class)
    @JmsListener(destination = "${artemis.queue.data_storage.set_user_avatar}", containerFactory = QUEUE)
    public void setUserAvatar(Message message) {
        UserSetAvatarRequestModel requestModel = this.jmsHelper.getResultModel(message, UserSetAvatarRequestModel.class);

        this.userDataServices.updateAvatar(requestModel.getUsername(), requestModel.getAvatar());
    }


}
