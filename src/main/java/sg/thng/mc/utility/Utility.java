package sg.thng.mc.utility;

import org.bukkit.plugin.java.JavaPlugin;
import sg.thng.mc.utility.Commands.*;
import sg.thng.mc.utility.Listeners.EventExample;

import java.util.logging.Level;

public final class Utility extends JavaPlugin {
    CommandManager commandManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().log(Level.INFO, "Bootstrapping...");

        // Load config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Event Listeners
        getServer().getPluginManager().registerEvents(new EventExample(), this);
        getLogger().log(Level.INFO, "Loaded: Event - ping");

        // Initialize Commands
        this.commandManager = new CommandManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().log(Level.INFO, "Stopped.");
    }
}
