package sg.thng.mc.utility.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class FeedCommand extends CommandTemplate {
    public FeedCommand(JavaPlugin plugin) {
        super(plugin, "feed", "thng.utils.feed");
    }

    @Override
    boolean performCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return genericPlayerOnlyResponse();
    }

    @Override
    boolean performCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        player.setFoodLevel(20);
        player.sendMessage(ChatColor.GREEN + "You have been fed.");
        return true;
    }
}
