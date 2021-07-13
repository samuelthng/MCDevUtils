package sg.thng.mc.utility.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class CommandManager {
	public JavaPlugin plugin;
	public CommandMap commandMap;

	public CommandManager(JavaPlugin plugin) {
		this.plugin = plugin;
		this.registerCommands();
	}

	private void registerCommands() {
		new GodCommand(plugin); // - God
		new HealCommand(plugin); // - Heal
		new FeedCommand(plugin); // - Heal
		new FlyCommand(plugin); // - Fly
		new LagCommand(plugin); // - Lag
	}
}
