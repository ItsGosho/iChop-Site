package com.ichop.plugin.linkaccount.loaders;

import com.ichop.plugin.linkaccount.commands.LinkAccountCommand;
import com.ichop.plugin.linkaccount.commands.UnlinkAccountCommand;
import org.bukkit.Bukkit;

import javax.inject.Inject;

public class CommandLoader {

    private static final String LINK_ACCOUNT_COMMAND_NAME = "link-account";
    private static final String UNLINK_ACCOUNT_COMMAND_NAME = "unlink-account";

    private final LinkAccountCommand linkAccountCommand;
    private final UnlinkAccountCommand unlinkAccountCommand;

    @Inject
    public CommandLoader(LinkAccountCommand linkAccountCommand, UnlinkAccountCommand unlinkAccountCommand) {
        this.linkAccountCommand = linkAccountCommand;
        this.unlinkAccountCommand = unlinkAccountCommand;
    }

    public void loadAll() {
        Bukkit.getServer().getPluginCommand(LINK_ACCOUNT_COMMAND_NAME).setExecutor(this.linkAccountCommand);
        Bukkit.getServer().getPluginCommand(UNLINK_ACCOUNT_COMMAND_NAME).setExecutor(this.unlinkAccountCommand);

    }

    /*TODO: Link Remove*/
}
