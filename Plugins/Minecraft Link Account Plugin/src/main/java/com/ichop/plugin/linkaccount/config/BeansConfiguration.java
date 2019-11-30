package com.ichop.plugin.linkaccount.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ichop.plugin.linkaccount.LinkAccount;
import com.ichop.plugin.linkaccount.helpers.JmsHelper;
import com.ichop.plugin.linkaccount.helpers.JmsHelperImpl;
import com.ichop.plugin.linkaccount.repository.KeyRepository;
import com.ichop.plugin.linkaccount.repository.KeyRepositoryImpl;
import com.ichop.plugin.linkaccount.repository.LinkRepository;
import com.ichop.plugin.linkaccount.repository.LinkRepositoryImpl;
import com.ichop.plugin.linkaccount.services.KeyServices;
import com.ichop.plugin.linkaccount.services.KeyServicesImpl;
import com.ichop.plugin.linkaccount.services.LinkServices;
import com.ichop.plugin.linkaccount.services.LinkServicesImpl;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.Session;
import javax.persistence.EntityManager;

public class BeansConfiguration extends AbstractModule {

    private final LinkAccount linkAccount;

    public BeansConfiguration(LinkAccount linkAccount) {
        this.linkAccount = linkAccount;
    }

    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Override
    protected void configure() {
        super.bind(EntityManager.class).toInstance(EntityManagerConfiguration.getEntityManager());

        /*ActiveMQ*/
        super.bind(ActiveMQConnectionFactory.class).toInstance(ArtemisConfiguration.getConnectionFactory());
        super.bind(Connection.class).toInstance(ArtemisConfiguration.getConnection());
        super.bind(Session.class).toInstance(ArtemisConfiguration.getSession());

        /*Listeners*/
        /*super.bind(IsKeyValidListener.class).toInstance();*/

        /*Repositories:*/
        super.bind(KeyRepository.class).to(KeyRepositoryImpl.class);
        super.bind(LinkRepository.class).to(LinkRepositoryImpl.class);

        /*Services:*/
        super.bind(KeyServices.class).to(KeyServicesImpl.class);
        super.bind(LinkServices.class).to(LinkServicesImpl.class);

        super.bind(JmsHelper.class).to(JmsHelperImpl.class);
        super.bind(ObjectMapper.class).toInstance(new ObjectMapper());
        super.bind(LinkAccount.class).toInstance(this.linkAccount);
    }
}