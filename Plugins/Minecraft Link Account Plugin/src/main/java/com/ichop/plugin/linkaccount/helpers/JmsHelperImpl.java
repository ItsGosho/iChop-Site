package com.ichop.plugin.linkaccount.helpers;

import javax.inject.Inject;
import javax.jms.Session;

public class JmsHelperImpl implements JmsHelper {

    private final Session session;

    @Inject
    public JmsHelperImpl(Session session) {
        this.session = session;
    }
}
