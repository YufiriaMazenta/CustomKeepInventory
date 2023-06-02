package world.oasismc.customkeepinventory.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import world.oasismc.customkeepinventory.CustomKeepInventory;
import world.oasismc.customkeepinventory.listener.KeepInventoryListener;
import world.oasismc.customkeepinventory.msg.MsgSender;

import java.util.Collections;
import java.util.List;

public enum KeepInventoryCommand implements TabExecutor {

    INSTANCE;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MsgSender.sendMsg(sender, "&cThis command can only be used by player");
            return true;
        }
        Player player = (Player) sender;
        String tag = CustomKeepInventory.getInstance().getConfig().getString("keep_inventory_tag", "keepinventory");
        String hintMessage;
        if (!KeepInventoryListener.hasKeepInventoryTag(player)) {
            player.addScoreboardTag(tag);
            hintMessage = CustomKeepInventory.getInstance().getConfig().getString("message_keep_inventory_on");
        } else {
            player.removeScoreboardTag(tag);
            hintMessage = CustomKeepInventory.getInstance().getConfig().getString("message_keep_inventory_off");
        }
        MsgSender.sendMsg(sender, hintMessage);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.singletonList("");
    }

}
