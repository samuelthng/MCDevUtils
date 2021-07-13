package sg.thng.mc.utility.Runnables;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.logging.Level;

public class LagOMeter implements Runnable {
	private final JavaPlugin plugin;
	private Player player;
	private int taskId = -1;
	private int count = 0;

	public LagOMeter(JavaPlugin plugin, @Nullable Player player) {
		this(plugin);
		this.player = player;
	}

	public LagOMeter(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
		if (taskId != -1) announce("Scheduled");
	}

	private double[] getTPS() {
		return plugin.getServer().getTPS();
	}

	private void onDisable() {
		if (taskId != -1) {
			plugin.getServer().getScheduler().cancelTask(taskId);
			announce("Stopped");
		}
	}

	private void announceFirstStart() {
		if (count == 0) announce("Started");
	}

	private void announceTPS() {
		count++;
		double[] TPS = getTPS();
		announce(Arrays.toString(TPS));
	}

	private void announce(String msg) {
		String message = "[Lag-O-Meter][" + taskId + "] " + msg;
		if (player != null) player.sendMessage(ChatColor.YELLOW + message);
		plugin.getServer().getLogger().log(Level.INFO, message);
	}

	@Override
	public void run() {
		announceFirstStart();
		announceTPS();
		if (count == 5) onDisable();
	}
}
