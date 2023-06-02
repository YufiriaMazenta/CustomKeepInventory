package world.oasismc.customkeepinventory.listener;

import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import world.oasismc.customkeepinventory.CustomKeepInventory;

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
        String tag = CustomKeepInventory.getInstance().getConfig().getString("keep_inventory_tag", "keepinventory");
        return player.getScoreboardTags().contains(tag);
    }

}
