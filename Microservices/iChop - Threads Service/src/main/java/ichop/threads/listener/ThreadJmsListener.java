package ichop.threads.listener;


import ichop.threads.domain.models.jms.create.ThreadCreateReplyModel;

import javax.jms.Message;

public interface ThreadJmsListener {


    ThreadCreateReplyModel createThread(Message message);
    void getById(Message message);
    void increaseViews(Message message);
    void deleteById(Message message);
}
