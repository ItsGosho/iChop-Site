package com.ichop.plugin.linkaccount;

import javax.jms.Message;
import javax.jms.MessageListener;

public class TestListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println();
    }
}
