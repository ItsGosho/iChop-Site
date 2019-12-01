package com.ichop.plugin.linkaccount.config;

import javax.inject.Inject;

public class ConfigurationRunner {

    private final ListenersConfiguration listenersConfiguration;

    @Inject
    public ConfigurationRunner(ListenersConfiguration listenersConfiguration) {
        this.listenersConfiguration = listenersConfiguration;
    }

    public void run() {
        this.listenersConfiguration.configure();
    }

}
