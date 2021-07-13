package sg.thng.mc.utility;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class utilities {
	public static boolean checkIsPlayer(@NotNull CommandSender sender) {
		return sender instanceof Player;
	}
}
