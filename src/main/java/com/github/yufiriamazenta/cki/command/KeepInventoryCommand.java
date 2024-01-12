package com.github.yufiriamazenta.cki.command;

import com.github.yufiriamazenta.cki.Configs;
import com.github.yufiriamazenta.cki.CustomKeepInventory;
import com.github.yufiriamazenta.cki.listener.KeepInventoryListener;
import com.github.yufiriamazenta.cki.msg.MsgSender;
import crypticlib.command.BukkitCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collections;
import java.util.List;

@BukkitCommand(
    name = "keepinventory",
    aliases = "ki",
    permission = "customkeepinventory.command"
)
public enum KeepInventoryCommand implements TabExecutor {

    INSTANCE;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MsgSender.sendMsg(sender, "&cThis command can only be used by player");
            return true;
        }
        Player player = (Player) sender;
        String hintMessage;
        if (!KeepInventoryListener.hasKeepInventoryTag(player)) {
            player.getPersistentDataContainer().set(CustomKeepInventory.INSTANCE.TAG, PersistentDataType.BYTE, (byte) 1);
            hintMessage = Configs.MESSAGE_KEEP_INVENTORY_ON.value();
        } else {
            player.getPersistentDataContainer().set(CustomKeepInventory.INSTANCE.TAG, PersistentDataType.BYTE, (byte) 0);
            hintMessage = Configs.MESSAGE_KEEP_INVENTORY_OFF.value();
        }
        MsgSender.sendMsg(sender, hintMessage);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.singletonList("");
    }

}
