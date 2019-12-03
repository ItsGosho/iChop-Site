package com.ichop.plugin.linkaccount.commands;

import com.ichop.plugin.linkaccount.domain.models.binding.KeyCreateBindingModel;
import com.ichop.plugin.linkaccount.domain.models.service.KeyServiceModel;
import com.ichop.plugin.linkaccount.services.KeyServices;
import com.ichop.plugin.linkaccount.services.LinkServices;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.inject.Inject;

public class UnlinkAccountCommand implements CommandExecutor {


    private final LinkServices linkServices;

    @Inject
    public UnlinkAccountCommand(LinkServices linkServices) {
        this.linkServices = linkServices;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = Bukkit.getPlayer(((Player) sender).getUniqueId());
        String playerUUID = player.getUniqueId().toString();

        if(!this.linkServices.isAccountLinkedByPlayerUUID(playerUUID)){
            sender.sendMessage(ChatColor.DARK_RED + "You have not linked your account!");
            return false;
        }

        this.linkServices.unlinkByPlayerUUID(playerUUID);

        sender.sendMessage(ChatColor.GREEN + "You have unlinked your account!");
        return true;
    }

}
