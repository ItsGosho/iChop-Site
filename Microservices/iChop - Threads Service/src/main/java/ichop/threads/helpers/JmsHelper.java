package ichop.threads.helpers;

import ichop.threads.domain.models.jms.BaseReceiveModel;
import ichop.threads.domain.models.jms.BaseSendModel;

import javax.jms.Message;

public interface JmsHelper {

    <S extends BaseSendModel,R extends BaseReceiveModel> R sendAndReceive(String destination, S model, Class<R> clazz);

    <S extends BaseSendModel> void send(String destination, S model);

    <S extends BaseSendModel> void replyTo(String destination, String correlationId, S model);

    <R extends BaseReceiveModel> R getResultModel(Message message, Class<R> clazz);

    <S extends BaseReceiveModel> void replyValidationError(Message message, S receiveModel);
}
