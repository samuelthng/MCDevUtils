package sg.thng.mc.utility.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class FlyCommand extends CommandTemplate {
    public FlyCommand(JavaPlugin plugin) {
        super(plugin, "fly", "thng.utils.fly");
    }

    @Override
    boolean performCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return genericPlayerOnlyResponse();
    }

    @Override
    boolean performCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        boolean willAllowFlight = !player.getAllowFlight();
        player.sendMessage(willAllowFlight ? "Fly enabled." : "Fly disabled.");
        player.setAllowFlight(willAllowFlight);
        return true;
    }
}
