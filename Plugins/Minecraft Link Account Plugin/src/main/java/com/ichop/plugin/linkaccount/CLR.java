package com.ichop.plugin.linkaccount;

import com.google.inject.Injector;
import com.ichop.plugin.linkaccount.commons.Plugin;
import com.ichop.plugin.linkaccount.config.BeansConfiguration;
import com.ichop.plugin.linkaccount.config.ConfigurationRunner;
import com.ichop.plugin.linkaccount.domain.models.service.KeyServiceModel;
import com.ichop.plugin.linkaccount.services.KeyServices;

import javax.inject.Inject;
import javax.jms.JMSException;

public class CLR {

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
