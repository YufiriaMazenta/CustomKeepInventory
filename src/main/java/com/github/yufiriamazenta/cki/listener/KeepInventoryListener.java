package com.github.yufiriamazenta.cki.listener;

import com.github.yufiriamazenta.cki.Configs;
import com.github.yufiriamazenta.cki.CustomKeepInventory;
import com.github.yufiriamazenta.cki.command.KeepInventoryCommand;
import crypticlib.listener.BukkitListener;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

@BukkitListener
public enum KeepInventoryListener implements Listener {

    INSTANCE;

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        World world = event.getEntity().getWorld();
        if (Boolean.TRUE.equals(world.getGameRuleValue(GameRule.KEEP_INVENTORY)))
            return;
        keepInventory(hasKeepInventoryTag(event.getEntity()), event);
    }

    private void keepInventory(boolean condition, PlayerDeathEvent event) {
        if (!condition)
            return;
        event.setKeepInventory(true);
        event.setKeepLevel(true);
        event.setDroppedExp(0);
        event.getDrops().clear();
    }

    public static boolean hasKeepInventoryTag(Player player) {
        PersistentDataContainer dataContainer = player.getPersistentDataContainer();
        boolean has = dataContainer.has(CustomKeepInventory.INSTANCE.TAG, PersistentDataType.BYTE);
        if (!has)
            return Configs.DEFAULT_KEEP_INVENTORY.value();
        Byte b = dataContainer.get(CustomKeepInventory.INSTANCE.TAG, PersistentDataType.BYTE);
        if (b == null)
            return false;
        return b >= 1;
    }

}
