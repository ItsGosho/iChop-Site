package com.ichop.plugin.linkaccount.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ichop.plugin.linkaccount.LinkAccount;
import com.ichop.plugin.linkaccount.database.EntityManagerCreator;
import com.ichop.plugin.linkaccount.domain.entities.Key;
import com.ichop.plugin.linkaccount.repository.KeyRepository;
import com.ichop.plugin.linkaccount.repository.KeyRepositoryImp;
import com.ichop.plugin.linkaccount.services.KeyServices;
import com.ichop.plugin.linkaccount.services.KeyServicesImp;

import javax.persistence.EntityManager;

public class InjectorConfigurations extends AbstractModule {

    private final LinkAccount linkAccount;

    public InjectorConfigurations(LinkAccount linkAccount) {
        this.linkAccount = linkAccount;
    }

    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Override
    protected void configure() {

        EntityManager entityManager = new EntityManagerCreator().createEntityManager("jdbc:mysql://localhost:3306/linkaccount?useSSL=false&amp;createDatabaseIfNotExist=true&amp;serverTimezone=UTC",
                "root","1234", Key.class);
        this.bind(EntityManager.class).toInstance(entityManager);
        this.bind(KeyRepository.class).to(KeyRepositoryImp.class);
        this.bind(KeyServices.class).to(KeyServicesImp.class);
        this.bind(ObjectMapper.class).toInstance(new ObjectMapper());
        this.bind(LinkAccount.class).toInstance(this.linkAccount);
    }
}
