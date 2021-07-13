package sg.thng.mc.utility.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class GodCommand extends CommandTemplate {
    public GodCommand(JavaPlugin plugin) {
        super(plugin, "god", "thng.utils.god");
    }

    @Override
    boolean performCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return genericPlayerOnlyResponse();
    }

    @Override
    boolean performCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        boolean isInvulnerable = player.isInvulnerable();
        player.sendMessage(isInvulnerable ? "Turning off god mode." : "Turning on god mode.");
        player.setInvulnerable(!isInvulnerable);
        return true;
    }
}
