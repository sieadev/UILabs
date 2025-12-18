package dev.siea.uilabs.listeners;

import dev.siea.uilabs.session.GuiSessionManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        GuiSessionManager.remove(event.getPlayer());
    }
}
