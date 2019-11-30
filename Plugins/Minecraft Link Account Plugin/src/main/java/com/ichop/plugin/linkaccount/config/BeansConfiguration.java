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
import com.ichop.plugin.linkaccount.services.KeyServices;
import com.ichop.plugin.linkaccount.services.KeyServicesImpl;

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
        super.bind(EntityManager.class).toInstance(EntityManagerConfiguration.entityManager());
        super.bind(KeyRepository.class).to(KeyRepositoryImpl.class);
        super.bind(KeyServices.class).to(KeyServicesImpl.class);
        super.bind(JmsHelper.class).to(JmsHelperImpl.class);
        super.bind(ObjectMapper.class).toInstance(new ObjectMapper());
        super.bind(LinkAccount.class).toInstance(this.linkAccount);
    }
}
