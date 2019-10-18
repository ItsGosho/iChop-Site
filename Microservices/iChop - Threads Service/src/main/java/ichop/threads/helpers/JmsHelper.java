package ichop.threads.helpers;

import ichop.threads.domain.models.jms.BaseRequestModel;
import ichop.threads.domain.models.jms.BaseReplyModel;

import javax.jms.Message;

public interface JmsHelper {

    <S extends BaseReplyModel,R extends BaseRequestModel> R sendAndReceive(String destination, S model, Class<R> clazz);

    <S extends BaseReplyModel> void send(String destination, S model);

    <S extends BaseReplyModel> void replySuccessful(Message message, S model);

    <R extends BaseRequestModel> R getResultModel(Message message, Class<R> clazz);

    <S extends BaseRequestModel> void replyValidationError(Message message, S receiveModel);
}
