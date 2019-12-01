package com.ichop.plugin.linkaccount.listeners;

import com.ichop.plugin.linkaccount.commons.domain.BoolReply;
import com.ichop.plugin.linkaccount.commons.helpers.JmsHelper;
import com.ichop.plugin.linkaccount.domain.models.jms.IsKeyValidRequest;
import com.ichop.plugin.linkaccount.services.KeyServices;

import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

public class IsKeyValidListener implements MessageListener {

    private final JmsHelper jmsHelper;
    private final KeyServices keyServices;

    @Inject
    public IsKeyValidListener(JmsHelper jmsHelper, KeyServices keyServices) {
        this.jmsHelper = jmsHelper;
        this.keyServices = keyServices;
    }


    @Override
    public void onMessage(Message message) {
        IsKeyValidRequest request = this.jmsHelper.toModel(message, IsKeyValidRequest.class);

        boolean isValid = this.keyServices.isValid(request.getLinkKey());

        BoolReply boolReply = new BoolReply(isValid);
        this.jmsHelper.replySuccessful(message, boolReply, "Fetch successful!");
    }

}
