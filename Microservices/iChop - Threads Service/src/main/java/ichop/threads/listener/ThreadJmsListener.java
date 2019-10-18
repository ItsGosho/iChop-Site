package ichop.threads.listener;


import javax.jms.Message;

public interface ThreadJmsListener {


    void createThread(Message message);
    void getById(Message message);
    void increaseViews(Message message);
    void deleteById(Message message);
}
