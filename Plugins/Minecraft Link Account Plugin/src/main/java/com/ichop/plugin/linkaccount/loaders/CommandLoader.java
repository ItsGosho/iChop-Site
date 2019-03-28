package com.ichop.plugin.linkaccount.loaders;

import com.ichop.plugin.linkaccount.commands.LinkAccountCommand;
import org.bukkit.Bukkit;

import javax.inject.Inject;

public class CommandLoader {

    private static final String LINK_ACCOUNT_COMMAND_NAME = "linkaccount";

    private final LinkAccountCommand linkAccountCommand;

    @Inject
    public CommandLoader(LinkAccountCommand linkAccountCommand) {
        this.linkAccountCommand = linkAccountCommand;
    }

    public void loadAll(){
        Bukkit.getServer().getPluginCommand(LINK_ACCOUNT_COMMAND_NAME).setExecutor(this.linkAccountCommand);

    }

}
