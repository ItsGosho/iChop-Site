package org.ichop.commons.helpers;

import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.domain.ReplyCandidate;
import org.ichop.commons.domain.RequestCandidate;

import javax.jms.Message;

public interface JmsHelper {


    <R extends RequestCandidate> JmsReplyModel sendAndReceive(String destination, R request);

    <R extends RequestCandidate> void send(String destination, R request);

    <R extends ReplyCandidate> void replySuccessful(Message message, R reply, String msg);

    JmsReplyModel extractReply(Message message);

    void replyValidationError(Message message, Object data);

    <R> R toModel(Message message, Class<R> clazz);

    <R extends ReplyCandidate> R extractReplyData(Message message, Class<R> clazz);

    <R extends ReplyCandidate> void replyError(Message message, R reply, String msg);
}
