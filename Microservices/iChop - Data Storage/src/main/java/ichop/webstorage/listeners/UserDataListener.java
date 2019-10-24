package ichop.webstorage.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.webstorage.common.aop.JmsValidate;
import ichop.webstorage.common.helpers.BaseListener;
import ichop.webstorage.common.helpers.JmsHelper;
import ichop.webstorage.models.UserSetAvatarRequestModel;
import ichop.webstorage.services.UserDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.webstorage.common.constants.JmsFactories.QUEUE;

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
