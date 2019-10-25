package ichop.storage.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.storage.common.aop.JmsValidate;
import ichop.storage.common.helpers.BaseListener;
import ichop.storage.common.helpers.JmsHelper;
import ichop.storage.models.UserSetAvatarRequest;
import ichop.storage.helpers.UserDataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.storage.common.constants.JmsFactories.QUEUE;

@Component
public class UserDataListener extends BaseListener {

    private final UserDataHelper userDataHelper;

    @Autowired
    protected UserDataListener(JmsHelper jmsHelper, ObjectMapper objectMapper, UserDataHelper userDataHelper) {
        super(jmsHelper, objectMapper);
        this.userDataHelper = userDataHelper;
    }

    @JmsValidate(model = UserSetAvatarRequest.class)
    @JmsListener(destination = "${artemis.queue.data_storage.set_user_avatar}", containerFactory = QUEUE)
    public void setUserAvatar(Message message) {
        UserSetAvatarRequest requestModel = this.jmsHelper.getResultModel(message, UserSetAvatarRequest.class);

        this.userDataHelper.updateAvatar(requestModel.getUsername(), requestModel.getAvatar());
    }


}