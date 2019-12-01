package com.ichop.plugin.linkaccount.listeners;

import com.ichop.plugin.linkaccount.commons.helpers.JmsHelper;
import com.ichop.plugin.linkaccount.domain.models.jms.LinkRetrieveJmsRequest;
import com.ichop.plugin.linkaccount.domain.models.service.LinkServiceModel;
import com.ichop.plugin.linkaccount.services.LinkServices;

import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

public class LinkRetrieveListener implements MessageListener {

    private final JmsHelper jmsHelper;
    private final LinkServices linkServices;

    @Inject
    public LinkRetrieveListener(JmsHelper jmsHelper, LinkServices linkServices) {
        this.jmsHelper = jmsHelper;
        this.linkServices = linkServices;
    }

    @Override
    public void onMessage(Message message) {
        LinkRetrieveJmsRequest request = this.jmsHelper.toModel(message, LinkRetrieveJmsRequest.class);

        LinkServiceModel link = this.linkServices.findByCandidateUID(request.getCandidateUID());

        if (link == null) {
            this.jmsHelper.replyValidationError(message, "Link not found!");
            return;
        }

        this.jmsHelper.replySuccessful(message, link, "Fetch successful!");
    }

}
