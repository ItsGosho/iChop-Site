package ichop.threads.helpers;

import ichop.threads.domain.models.jms.BaseSendModel;

import javax.jms.Message;

public interface JmsHelper {

   <S,R> R sendAndReceive(String destination, S model, Class<R> clazz);

   <S> void send(String destination, S model);

   <S> void replyTo(String destination, String correlationId, S model);

   <R> R getResultModel(Message message, Class<R> clazz);
}
