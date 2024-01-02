package com.github.yufiriamazenta.cki;

import com.github.yufiriamazenta.cki.msg.MsgSender;
import crypticlib.BukkitPlugin;
import crypticlib.CrypticLib;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;

public final class CustomKeepInventory extends BukkitPlugin {

    public static CustomKeepInventory INSTANCE;
    public final NamespacedKey TAG = new NamespacedKey(this, "keep-inventory");

    @Override
    public void enable() {
        INSTANCE = this;
        String loadMsg;
        if (CrypticLib.minecraftVersion() >= 11600) {
            loadMsg = "&7[&#00AE00C&#0DBB0Du&#00AE1As&#0DBB28t&#00AE35o&#0DBB43m&#00AE50K&#0DBB5De&#00AE6Be&#0DBB78p&#00AE86I&#0DBB93n&#00AEA1v&#0DBBAEe&#00AEBBn&#0DBBC9t&#00AED6o&#0DBBE4r&#00AEF1y&7] &#00AEE9P&#00AED4l&#00AEBFu&#00AEAAg&#00AE94i&#00AE7Fn &#00AE6AL&#00AE55o&#00AE3Fa&#00AE2Ad&#00AE15e&#00AE00d";
        } else
            loadMsg = "&7[&3Custom&bKeepInventory&7] &3Plugin Loaded";
        MsgSender.sendMsg(Bukkit.getConsoleSender(), loadMsg);
    }

    @Override
    public void disable() {
        CrypticLib.platform().scheduler().cancelTasks(this);
    }

}
