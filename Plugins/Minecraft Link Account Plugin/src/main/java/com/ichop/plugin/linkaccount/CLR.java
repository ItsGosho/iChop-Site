package com.ichop.plugin.linkaccount;

import com.google.inject.Injector;
import com.ichop.plugin.linkaccount.commons.Plugin;
import com.ichop.plugin.linkaccount.config.BeansConfiguration;
import com.ichop.plugin.linkaccount.config.ConfigurationRunner;
import com.ichop.plugin.linkaccount.loaders.CommandLoader;

import javax.inject.Inject;
import javax.jms.JMSException;

public class CLR {

    public static final String IS_ACCOUNT_LINKED_URL = "http://localhost:8000/player/is-account-linked?uuid={uuid}";

    @Inject
    private LinkAccount linkAccount;


    public static void main(String[] args) throws JMSException, InterruptedException {

        ManualTest manualTest = new ManualTest();
        manualTest.run();

        while (true) {
            Thread.sleep(50);
        }
    }

    public static class ManualTest implements Plugin {

        @Inject
        private ConfigurationRunner configurationRunner;

        public void run() {
            BeansConfiguration beansConfiguration = new BeansConfiguration(this);
            Injector injector = beansConfiguration.createInjector();
            injector.injectMembers(this);

            this.configurationRunner.run();
        }
    }
}
