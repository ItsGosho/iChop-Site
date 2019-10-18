package ichop.threads.listener;


import ichop.threads.domain.models.jms.create.ThreadCreateReplyModel;
import ichop.threads.domain.models.jms.delete.ThreadDeleteByIdReplyModel;
import ichop.threads.domain.models.jms.increase.ThreadIncreaseViewsReplyModel;
import ichop.threads.domain.models.jms.retrieve.ThreadGetByIdReplyModel;

import javax.jms.Message;

public interface ThreadJmsListener {


    ThreadCreateReplyModel createThread(Message message);
    ThreadGetByIdReplyModel getById(Message message);
    ThreadIncreaseViewsReplyModel increaseViews(Message message);
    ThreadDeleteByIdReplyModel deleteById(Message message);
}
