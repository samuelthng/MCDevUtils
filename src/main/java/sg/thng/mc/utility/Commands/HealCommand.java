package sg.thng.mc.utility.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class HealCommand extends CommandTemplate {
    public HealCommand(JavaPlugin plugin) {
        super(plugin, "heal", "thng.utils.heal");
    }

    @Override
    boolean performCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return genericPlayerOnlyResponse();
    }

    @Override
    boolean performCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        player.setHealth(20);
        player.setFoodLevel(20);
        player.sendMessage("You have been healed and fed.");
        return true;
    }
}
