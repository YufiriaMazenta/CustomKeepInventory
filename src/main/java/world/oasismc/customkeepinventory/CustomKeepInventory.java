package world.oasismc.customkeepinventory;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import world.oasismc.customkeepinventory.command.KeepInventoryCommand;
import world.oasismc.customkeepinventory.listener.KeepInventoryListener;
import world.oasismc.customkeepinventory.msg.MsgSender;

public final class CustomKeepInventory extends JavaPlugin {

    private static CustomKeepInventory INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(KeepInventoryListener.INSTANCE, this);
        Bukkit.getPluginCommand("keepinventory").setExecutor(KeepInventoryCommand.INSTANCE);
        Bukkit.getPluginCommand("keepinventory").setTabCompleter(KeepInventoryCommand.INSTANCE);
        String loadMsg;
        if (MsgSender.vanillaVersion >= 16) {
            loadMsg = "&7[&#00AE00C&#0DBB0Du&#00AE1As&#0DBB28t&#00AE35o&#0DBB43m&#00AE50K&#0DBB5De&#00AE6Be&#0DBB78p&#00AE86I&#0DBB93n&#00AEA1v&#0DBBAEe&#00AEBBn&#0DBBC9t&#00AED6o&#0DBBE4r&#00AEF1y&7] &#00AEE9P&#00AED4l&#00AEBFu&#00AEAAg&#00AE94i&#00AE7Fn &#00AE6AL&#00AE55o&#00AE3Fa&#00AE2Ad&#00AE15e&#00AE00d";
        } else
            loadMsg = "&7[&3Custom&bKeepInventory&7] &3Plugin Loaded";
        MsgSender.sendMsg(Bukkit.getConsoleSender(), loadMsg);
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }

    @Override
    public FileConfiguration getConfig() {
        reloadConfig();
        return super.getConfig();
    }

    public static CustomKeepInventory getInstance() {
        return INSTANCE;
    }

}
