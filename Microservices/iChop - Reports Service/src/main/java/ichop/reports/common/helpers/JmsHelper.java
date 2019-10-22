package ichop.reports.common.helpers;

import ichop.reports.common.domain.BaseReplyModel;
import ichop.reports.common.domain.BaseRequestModel;

import javax.jms.Message;

public interface JmsHelper {

    <S extends BaseReplyModel,R extends BaseRequestModel> S sendAndReceive(String destination, R model, Class<S> clazz);

    <S extends BaseRequestModel> void send(String destination, S model);

    <S extends BaseReplyModel> void replySuccessful(Message message, S model);

    <R> R getResultModel(Message message, Class<R> clazz);

    <S extends BaseRequestModel> void replyValidationError(Message message, S receiveModel);
}
