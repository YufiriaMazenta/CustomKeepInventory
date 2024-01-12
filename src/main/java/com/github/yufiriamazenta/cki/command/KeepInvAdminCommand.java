package com.github.yufiriamazenta.cki.command;

import com.github.yufiriamazenta.cki.Configs;
import com.github.yufiriamazenta.cki.CustomKeepInventory;
import com.github.yufiriamazenta.cki.msg.MsgSender;
import crypticlib.command.BukkitCommand;
import crypticlib.command.RootCmdExecutor;

import static crypticlib.command.CommandManager.subcommand;

@BukkitCommand(
    name = "keepinventoryadmin",
    aliases = {"kia", "keepinvadmin"},
    permission = "customkeepinventory.command.admin"
)
public class KeepInvAdminCommand extends RootCmdExecutor {

    public KeepInvAdminCommand() {
        regSub(subcommand("reload")
            .setExecutor((sender, args) -> {
                CustomKeepInventory.INSTANCE.reloadConfig();
                MsgSender.sendMsg(sender, Configs.MESSAGE_COMMAND_RELOAD.value());
                return true;
            })
            .setPermission("customkeepinventory.command.admin.reload")
        );
    }

}
