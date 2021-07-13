package sg.thng.mc.utility.Runnables;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.logging.Level;

public class LagOMeter implements Runnable {
	JavaPlugin plugin;
	private boolean started = false;

	public LagOMeter(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	private double[] getTPS() {
		return plugin.getServer().getTPS();
	}

	private void announceFirstStart() {
		if (!started) {
			started = true;
			plugin.getServer().getLogger().log(Level.INFO, "Lag-O-Meter: Started.");
		}
	}

	private void announceTPS() {
		double[] TPS = getTPS();
		plugin.getServer().getLogger().log(Level.INFO, "Lag-O-Meter: " + Arrays.toString(TPS));
	}

	@Override
	public void run() {
		announceFirstStart();
		announceTPS();
	}
}
