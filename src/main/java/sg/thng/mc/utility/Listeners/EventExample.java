package sg.thng.mc.utility.Listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.*;

public class EventExample implements Listener {
    @EventHandler
    void onPlayerChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        if (player instanceof Player) {
            Component ping = Component.text("Ping: ").append(event.message());
            player.sendMessage(ping);
        }
    }
}
