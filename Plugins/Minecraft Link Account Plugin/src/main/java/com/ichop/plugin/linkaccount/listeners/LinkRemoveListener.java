package com.ichop.plugin.linkaccount.listeners;

import com.ichop.plugin.linkaccount.commons.domain.EmptyReply;
import com.ichop.plugin.linkaccount.commons.helpers.JmsHelper;
import com.ichop.plugin.linkaccount.domain.models.binding.LinkCreateBindingModel;
import com.ichop.plugin.linkaccount.domain.models.jms.LinkCreateJmsRequest;
import com.ichop.plugin.linkaccount.domain.models.jms.LinkRemoveJmsRequest;
import com.ichop.plugin.linkaccount.domain.models.service.KeyServiceModel;
import com.ichop.plugin.linkaccount.services.KeyServices;
import com.ichop.plugin.linkaccount.services.LinkServices;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

public class LinkRemoveListener implements MessageListener {

    private final JmsHelper jmsHelper;
    private final LinkServices linkServices;

    @Inject
    public LinkRemoveListener(JmsHelper jmsHelper, LinkServices linkServices) {
        this.jmsHelper = jmsHelper;
        this.linkServices = linkServices;
    }

    @Override
    public void onMessage(Message message) {
        LinkRemoveJmsRequest request = this.jmsHelper.toModel(message, LinkRemoveJmsRequest.class);

        if (!this.linkServices.isAccountLinkedByCandidateUID(request.getCandidateUID())) {
            this.jmsHelper.replyValidationError(message, "Account is not linked!");
            return;
        }

        this.linkServices.unlinkByCandidateUID(request.getCandidateUID());

        this.jmsHelper.replySuccessful(message, new EmptyReply(), "Successful unlink!");
    }

}
