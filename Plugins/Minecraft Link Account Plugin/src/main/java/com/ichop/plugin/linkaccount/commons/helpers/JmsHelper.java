package com.ichop.plugin.linkaccount.commons.helpers;

import com.ichop.plugin.linkaccount.commons.domain.JmsReplyModel;

import javax.jms.Message;

public interface JmsHelper {

    void replySuccessful(Message message, Object reply, String msg);

    void replyValidationError(Message message, String error);

    JmsReplyModel extractReply(Message message);

    <R> R toModel(Message message, Class<R> clazz);

    <R> R extractReplyData(Message message, Class<R> clazz);

    <R> void replyError(Message message, R reply, String msg);
}
