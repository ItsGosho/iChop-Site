package com.ichop.plugin.linkaccount.listeners;

import com.ichop.plugin.linkaccount.commons.domain.EmptyReply;
import com.ichop.plugin.linkaccount.commons.helpers.JmsHelper;
import com.ichop.plugin.linkaccount.domain.models.binding.LinkCreateBindingModel;
import com.ichop.plugin.linkaccount.domain.models.jms.LinkCreateJmsRequest;
import com.ichop.plugin.linkaccount.domain.models.service.KeyServiceModel;
import com.ichop.plugin.linkaccount.services.KeyServices;
import com.ichop.plugin.linkaccount.services.LinkServices;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

public class LinkCreateListener implements MessageListener {

    private final JmsHelper jmsHelper;
    private final LinkServices linkServices;
    private final KeyServices keyServices;
    private final ModelMapper modelMapper;

    @Inject
    public LinkCreateListener(JmsHelper jmsHelper,
                              LinkServices linkServices,
                              KeyServices keyServices,
                              ModelMapper modelMapper) {
        this.jmsHelper = jmsHelper;
        this.linkServices = linkServices;
        this.keyServices = keyServices;
        this.modelMapper = modelMapper;
    }

    @Override
    public void onMessage(Message message) {
        LinkCreateJmsRequest request = this.jmsHelper.toModel(message, LinkCreateJmsRequest.class);

        if (!this.keyServices.isValid(request.getLinkKey())) {
            this.jmsHelper.replyValidationError(message, "The provided key is not valid!");
            return;
        }

        if(this.linkServices.isAccountLinkedByCandidateUID(request.getCandidateUID())){
            this.jmsHelper.replyValidationError(message, "Account is already linked!");
            return;
        }

        KeyServiceModel key = this.keyServices.findByLinkKey(request.getLinkKey());

        LinkCreateBindingModel bindingModel = this.modelMapper.map(request, LinkCreateBindingModel.class);
        bindingModel.setPlayerUUID(key.getPlayerUUID());

        this.linkServices.create(bindingModel);
        this.keyServices.deleteLastByKey(request.getLinkKey());

        this.jmsHelper.replySuccessful(message,new EmptyReply(),"Successful link!");
    }

}
