package dev.siea.uilabs.session;

import dev.siea.uilabs.gui.GuiInstance;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GuiSessionManager {

    private static final Map<Player, GuiSession> sessions = new ConcurrentHashMap<>();

    /** Returns the current session for a player or creates a new one */
    public static GuiSession get(Player player) {
        return sessions.computeIfAbsent(player, p -> new GuiSession(null, p));
    }

    /** Registers a new instance for the player */
    public static void register(Player player, GuiInstance instance) {
        GuiSession session = get(player);
        session.open(instance, true);
    }

    /** Closes and removes a session for cleanup */
    public static void remove(Player player) {
        GuiSession session = sessions.remove(player);
        if (session != null) {
            session.close();
        }
    }
}

