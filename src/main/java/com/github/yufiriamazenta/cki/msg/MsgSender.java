package com.github.yufiriamazenta.cki.msg;

import com.github.yufiriamazenta.cki.Configs;
import com.github.yufiriamazenta.cki.CustomKeepInventory;
import crypticlib.chat.MessageSender;
import crypticlib.chat.TextProcessor;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MsgSender {

    public static void sendMsg(CommandSender sender, String message) {
        if (message == null || sender == null)
            return;
        message = TextProcessor.color(message);
        if (!(sender instanceof Player)) {
            sender.sendMessage(message);
            return;
        }
        Player player = (Player) sender;
        String type = Configs.MESSAGE_TYPE.value();
        switch (type) {
            case "title":
                MessageSender.sendTitle(player, message, "");
                break;
            case "subtitle":
                MessageSender.sendTitle(player, "", message);
                break;
            case "message":
                MessageSender.sendMsg(player, message);
                break;
            case "actionbar":
            default:
                MessageSender.sendActionBar(player, message);
                break;
        }
    }

}
