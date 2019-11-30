package com.ichop.plugin.linkaccount.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ichop.plugin.linkaccount.LinkAccount;
import com.ichop.plugin.linkaccount.repository.KeyRepository;
import com.ichop.plugin.linkaccount.repository.KeyRepositoryImp;
import com.ichop.plugin.linkaccount.services.KeyServices;
import com.ichop.plugin.linkaccount.services.KeyServicesImp;

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
        this.bind(EntityManager.class).toInstance(EntityManagerConfiguration.entityManager());
        this.bind(KeyRepository.class).to(KeyRepositoryImp.class);
        this.bind(KeyServices.class).to(KeyServicesImp.class);
        this.bind(ObjectMapper.class).toInstance(new ObjectMapper());
        this.bind(LinkAccount.class).toInstance(this.linkAccount);
    }
}
