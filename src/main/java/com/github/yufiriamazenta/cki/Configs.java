package com.github.yufiriamazenta.cki;

import crypticlib.config.ConfigHandler;
import crypticlib.config.entry.StringConfigEntry;

@ConfigHandler(path = "config.yml")
public class Configs {

    public static final StringConfigEntry MESSAGE_TYPE = new StringConfigEntry("message_type", "actionbar");
    public static final StringConfigEntry MESSAGE_KEEP_INVENTORY_ON = new StringConfigEntry("message_keep_inventory_on", "&a死亡不掉落开启");
    public static final StringConfigEntry MESSAGE_KEEP_INVENTORY_OFF = new StringConfigEntry("message_keep_inventory_off", "&c死亡不掉落关闭");

}
