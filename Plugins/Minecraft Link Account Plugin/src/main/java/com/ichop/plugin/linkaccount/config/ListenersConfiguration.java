package com.ichop.plugin.linkaccount.config;

public class ListenersConfiguration {

    private static final String BASE_QUEUE_PREFIX = "link_account_plugin";
    private static final String IS_KEY_VALID_QUEUE = BASE_QUEUE_PREFIX + ".is.key.valid";
    private static final String LINK_CREATE_QUEUE = BASE_QUEUE_PREFIX + ".link.create";
    private static final String LINK_RETRIEVE_QUEUE = BASE_QUEUE_PREFIX + ".link.retrieve";

}
