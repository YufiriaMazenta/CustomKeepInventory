package world.oasismc.customkeepinventory.msg;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import world.oasismc.customkeepinventory.CustomKeepInventory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MsgSender {

    public static int vanillaVersion;

    private static final Pattern colorPattern = Pattern.compile("&#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})");

    static {
        loadVanillaVersion();
    }

    public static void sendMsg(CommandSender sender, String message) {
        if (message == null || sender == null)
            return;
        message = color(message);
        if (!(sender instanceof Player)) {
            sender.sendMessage(message);
            return;
        }
        Player player = (Player) sender;
        String type = CustomKeepInventory.getInstance().getConfig().getString("message_type", "actionbar");
        switch (type) {
            case "title":
                player.sendTitle(message, "", 10, 70, 20);
                break;
            case "subtitle":
                player.sendTitle("", message, 10, 70, 20);
                break;
            case "message":
                player.sendMessage(message);
                break;
            case "actionbar":
            default:
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
                break;
        }
    }

    public static String color(String msg) {
        if (vanillaVersion >= 16) {
            StringBuilder strBuilder = new StringBuilder(msg);
            Matcher matcher = colorPattern.matcher(strBuilder);
            while (matcher.find()) {
                String colorCode = matcher.group();
                String colorStr = ChatColor.of(colorCode.substring(1)).toString();
                strBuilder.replace(matcher.start(), matcher.start() + colorCode.length(), colorStr);
                matcher = colorPattern.matcher(strBuilder);
            }
            msg = strBuilder.toString();
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    private static void loadVanillaVersion() {
        String versionStr = Bukkit.getBukkitVersion();
        int index1 = versionStr.indexOf(".");
        int index2 = versionStr.indexOf(".", index1 + 1);
        versionStr = versionStr.substring(index1 + 1, index2);
        try {
            vanillaVersion = Integer.parseInt(versionStr);
        } catch (NumberFormatException e) {
            vanillaVersion = Integer.parseInt(versionStr.substring(0, versionStr.indexOf("-")));
        }
    }

}
