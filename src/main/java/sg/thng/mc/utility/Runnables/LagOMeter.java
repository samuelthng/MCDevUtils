package sg.thng.mc.utility.Runnables;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Level;

public class LagOMeter implements Runnable {
	private final JavaPlugin plugin;
	private final Player player;
	private int taskId = -1;
	private int count = 0;

	public LagOMeter(JavaPlugin plugin, @Nullable Player player) {
		this.plugin = plugin;
		this.player = player;
	}

	public boolean start() {
		taskId = plugin
						.getServer()
						.getScheduler()
						.scheduleSyncRepeatingTask(plugin, this, 0, 600);
		if (taskId != -1) announce("Scheduled");
		return taskId != -1;
	}

	private void stop() {
		if (taskId != -1) {
			plugin.getServer().getScheduler().cancelTask(taskId);
			announce("Stopped");
		}
	}

	@Override
	public void run() {
		announceFirstStart();
		announceTPS();
		if (count == 5) stop();
	}

	private double[] getTPS() {
		return plugin.getServer().getTPS();
	}

	private void announce(String msg) {
		String message = "[Lag-O-Meter][" + taskId + "] " + msg;
		if (player != null) player.sendMessage(ChatColor.YELLOW + message);
		plugin.getServer().getLogger().log(Level.INFO, message);
	}

	private void announceFirstStart() {
		if (count == 0) announce("Started");
	}

	private void announceTPS() {
		count++;
		double[] TPS = getTPS();
		Object[] TPSObject = ArrayUtils.toObject(TPS);
		announce(String.format("1 min: %.2f, 5 min: %.2f, 15 min: %.2f", TPSObject));
	}
}
