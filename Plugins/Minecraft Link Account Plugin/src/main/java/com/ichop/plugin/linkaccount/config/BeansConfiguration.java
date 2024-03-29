package com.ichop.plugin.linkaccount.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ichop.plugin.linkaccount.LinkAccount;
import com.ichop.plugin.linkaccount.commons.Plugin;
import com.ichop.plugin.linkaccount.commons.helpers.JmsHelperImpl;
import com.ichop.plugin.linkaccount.listeners.*;
import com.ichop.plugin.linkaccount.repository.KeyRepository;
import com.ichop.plugin.linkaccount.repository.KeyRepositoryImpl;
import com.ichop.plugin.linkaccount.repository.LinkRepository;
import com.ichop.plugin.linkaccount.repository.LinkRepositoryImpl;
import com.ichop.plugin.linkaccount.services.KeyServices;
import com.ichop.plugin.linkaccount.services.KeyServicesImpl;
import com.ichop.plugin.linkaccount.services.LinkServices;
import com.ichop.plugin.linkaccount.services.LinkServicesImpl;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.modelmapper.ModelMapper;
import com.ichop.plugin.linkaccount.commons.helpers.JmsHelper;

import javax.jms.Connection;
import javax.jms.Session;
import javax.persistence.EntityManager;

public class BeansConfiguration extends AbstractModule {

    private final Plugin plugin;

    public BeansConfiguration(Plugin plugin) {
        this.plugin = plugin;
    }

    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Override
    protected void configure() {
        super.bind(EntityManager.class).toInstance(EntityManagerConfiguration.entityManager());

        /*ActiveMQ*/
        super.bind(ActiveMQConnectionFactory.class).toInstance(ArtemisConfiguration.getConnectionFactory());
        super.bind(Connection.class).toInstance(ArtemisConfiguration.getConnection());
        super.bind(Session.class).toInstance(ArtemisConfiguration.getSession());
        super.bind(ListenersConfiguration.class).asEagerSingleton();
        super.bind(ConfigurationRunner.class).asEagerSingleton();

        /*Listeners*/
        super.bind(IsKeyValidListener.class).asEagerSingleton();
        super.bind(KeyRetrieveListener.class).asEagerSingleton();
        super.bind(LinkCreateListener.class).asEagerSingleton();
        super.bind(LinkRemoveListener.class).asEagerSingleton();
        super.bind(LinkRetrieveListener.class).asEagerSingleton();

        /*Repositories:*/
        super.bind(KeyRepository.class).to(KeyRepositoryImpl.class);
        super.bind(LinkRepository.class).to(LinkRepositoryImpl.class);

        /*Services:*/
        super.bind(KeyServices.class).to(KeyServicesImpl.class);
        super.bind(LinkServices.class).to(LinkServicesImpl.class);

        super.bind(JmsHelper.class).to(JmsHelperImpl.class);
        super.bind(ModelMapper.class).toInstance(new ModelMapper());
        super.bind(ObjectMapper.class).toInstance(new ObjectMapper());
        super.bind(Plugin.class).toInstance(this.plugin);
    }
}
