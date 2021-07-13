package sg.thng.mc.utility.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.logging.Level;

public abstract class CommandTemplate implements CommandExecutor {
	public String command;
	public String permission;
	private final JavaPlugin plugin;

	public CommandTemplate(JavaPlugin plugin, String command, String permission) {
		this.command = command;
		this.permission = permission;
		this.plugin = plugin;
		this.registerCommand();
	}

	public void registerCommand() {
		PluginCommand pluginCommand = plugin.getCommand(command);
		if (pluginCommand != null) {
			pluginCommand.setExecutor(this);
			pluginCommand.setPermission(permission);
			pluginCommand.setPermissionMessage(ChatColor.RED + "Invalid action: You do not have permissions for this command.");
		}
		plugin.getLogger().log(Level.INFO, "Loaded: Command - /" + command);
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		boolean isValidCommand = validateAndLogPlayerPermission(sender);
		if (!isValidCommand) return false;
		if (sender instanceof Player) return performCommand((Player) sender, command, label, args);
		return performCommand(sender, command, label, args);
	}

	private boolean validateAndLogPlayerPermission(@NotNull CommandSender sender) {
		if (sender instanceof Player && permission != null) {
			Player player = (Player) sender;
			return player.hasPermission(permission) || genericPermissionDeniedResponse(player);
		}
		return true;
	}

	public boolean genericPermissionDeniedResponse(@NotNull Player player) {
//		player.sendMessage(ChatColor.RED + "Invalid action: You do not have permissions for this command.");
		String logTemplate = "{0} attempted to run /{1} without permission node {2} and failed.";
		Object[] logParams = new Object[]{player.getName(), command, permission};
		plugin.getLogger().log(Level.INFO, MessageFormat.format(logTemplate, logParams));
		return false;
	}

	public boolean genericPlayerOnlyResponse() {
		plugin.getLogger().log(Level.WARNING, ChatColor.RED + "Invalid action: This is a player only command.");
		return false;
	}

	abstract boolean performCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args);

	abstract boolean performCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args);
}
