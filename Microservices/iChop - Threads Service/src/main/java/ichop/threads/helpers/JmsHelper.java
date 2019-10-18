package ichop.threads.helpers;

import ichop.threads.domain.models.jms.BaseReceiveModel;
import ichop.threads.domain.models.jms.BaseReplyModel;

import javax.jms.Message;

public interface JmsHelper {

    <S extends BaseReplyModel,R extends BaseReceiveModel> R sendAndReceive(String destination, S model, Class<R> clazz);

    <S extends BaseReplyModel> void send(String destination, S model);

    <S extends BaseReplyModel> void replySuccessful(Message message, S model);

    <R extends BaseReceiveModel> R getResultModel(Message message, Class<R> clazz);

    <S extends BaseReceiveModel> void replyValidationError(Message message, S receiveModel);
}
