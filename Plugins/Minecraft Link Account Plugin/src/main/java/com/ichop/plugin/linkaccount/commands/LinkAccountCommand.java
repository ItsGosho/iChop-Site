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

public class LinkAccountCommand implements CommandExecutor {

    public static final String PLAYER_LINK_ACCOUNT_GET = "localhost:8000/player/link-account?key={key}";

    private final KeyServices keyServices;
    private final LinkServices linkServices;

    @Inject
    public LinkAccountCommand(KeyServices keyServices, LinkServices linkServices) {
        this.keyServices = keyServices;
        this.linkServices = linkServices;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = Bukkit.getPlayer(((Player) sender).getUniqueId());
        String playUUID = player.getUniqueId().toString();

        if(this.linkServices.isAccountLinkedByPlayerUUID(playUUID)){
            sender.sendMessage(ChatColor.DARK_RED + "You have already linked your account!");
            return false;
        }

        KeyCreateBindingModel keyCreateBindingModel = new KeyCreateBindingModel();
        keyCreateBindingModel.setPlayerUUID(player.getUniqueId().toString());
        keyCreateBindingModel.setPlayerName(player.getName());

        KeyServiceModel key = this.keyServices.create(keyCreateBindingModel);
        String url = PLAYER_LINK_ACCOUNT_GET.replace("{key}", key.getKey());

        this.sendCommandSuccessfulMessage(sender,url);

        return true;
    }

    private void sendCommandSuccessfulMessage(CommandSender sender,String url){
        sender.sendMessage(ChatColor.DARK_RED + "------------------------------------------------------------->");
        sender.sendMessage(ChatColor.DARK_GREEN + "Open this link to link your account:");
        sender.sendMessage(ChatColor.GRAY + "LINK: " + ChatColor.RED + url);
        sender.sendMessage(ChatColor.DARK_GREEN + "Its valid " + ChatColor.RED + "3"+ ChatColor.DARK_GREEN+" minutes ,then you need to use /linkaccount again for a new link");
        sender.sendMessage(ChatColor.DARK_GRAY + "NOTE:" + ChatColor.GREEN + " You must be logged in the site into the account that you want to link!");
        sender.sendMessage(ChatColor.DARK_RED + "------------------------------------------------------------->");
    }

}
