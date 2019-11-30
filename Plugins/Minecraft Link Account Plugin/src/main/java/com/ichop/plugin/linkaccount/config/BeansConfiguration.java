package com.ichop.plugin.linkaccount.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ichop.plugin.linkaccount.LinkAccountPlugin;
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

import javax.persistence.EntityManager;

public class BeansConfiguration extends AbstractModule {

    private final LinkAccountPlugin linkAccountPlugin;

    public BeansConfiguration(LinkAccountPlugin linkAccountPlugin) {
        this.linkAccountPlugin = linkAccountPlugin;
    }

    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Override
    protected void configure() {
        super.bind(EntityManager.class).toInstance(EntityManagerConfiguration.entityManager());

        /*Repositories:*/
        super.bind(KeyRepository.class).to(KeyRepositoryImpl.class);
        super.bind(LinkRepository.class).to(LinkRepositoryImpl.class);

        /*Services:*/
        super.bind(KeyServices.class).to(KeyServicesImpl.class);
        super.bind(LinkServices.class).to(LinkServicesImpl.class);

        super.bind(JmsHelper.class).to(JmsHelperImpl.class);
        super.bind(ObjectMapper.class).toInstance(new ObjectMapper());
        super.bind(LinkAccountPlugin.class).toInstance(this.linkAccountPlugin);
    }
}
