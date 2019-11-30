package com.ichop.plugin.linkaccount;

import com.google.inject.Injector;
import com.ichop.plugin.linkaccount.config.ArtemisConfiguration;
import com.ichop.plugin.linkaccount.config.BeansConfiguration;
import com.ichop.plugin.linkaccount.loaders.CommandLoader;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;

public final class LinkAccountPlugin extends JavaPlugin {

    @Inject
    private CommandLoader commandLoader;

    @Override
    public void onEnable() {

        BeansConfiguration beansConfiguration = new BeansConfiguration(this);
        Injector injector = beansConfiguration.createInjector();
        injector.injectMembers(this);

        this.commandLoader.loadAll();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
