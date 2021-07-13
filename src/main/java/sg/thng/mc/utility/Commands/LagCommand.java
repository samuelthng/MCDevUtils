package sg.thng.mc.utility.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sg.thng.mc.utility.Runnables.LagOMeter;

public class LagCommand extends CommandTemplate {

	public LagCommand(JavaPlugin plugin) {
		super(plugin, "lag", "thng.utils.lag");
	}

	@Override
	boolean performCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		return scheduleLagOMeter(null);
	}

	@Override
	boolean performCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		return scheduleLagOMeter(player);
	}

	private boolean scheduleLagOMeter(@Nullable Player player) {
		LagOMeter lagOMeter = new LagOMeter(plugin, player);
		int taskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, lagOMeter, 0, 600);
		lagOMeter.setTaskId(taskId);
		return taskId != -1;
	}
}
