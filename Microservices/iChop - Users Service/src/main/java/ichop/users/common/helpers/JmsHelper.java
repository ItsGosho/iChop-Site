package ichop.users.common.helpers;

import ichop.users.common.domain.BaseReplyModel;
import ichop.users.common.domain.BaseRequestModel;

import javax.jms.Message;

public interface JmsHelper {

    <S extends BaseReplyModel,R extends BaseRequestModel> S sendAndReceive(String destination, R model, Class<S> clazz);

    <S extends BaseRequestModel> void send(String destination, S model);

    <S extends BaseReplyModel> void replySuccessful(Message message, S model, String msg);

    <R> R getResultModel(Message message, Class<R> clazz);

    <S extends BaseRequestModel> void replyValidationError(Message message, S receiveModel);
}
