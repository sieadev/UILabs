package dev.siea.uilabs.router;

import dev.siea.uilabs.gui.GuiDefinition;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Central router for GUIs.
 * Stores GuiDefinitions by route name and allows players to open GUIs by route.
 */
public class GuiRouter {

    private static final Map<String, GuiDefinition> routes = new ConcurrentHashMap<>();

    /** Register a route with a GuiDefinition */
    public static void register(String route, GuiDefinition definition) {
        if (route == null || definition == null) {
            throw new IllegalArgumentException("Route and definition cannot be null");
        }
        routes.put(route, definition);
    }

    /** Unregister a route */
    public static void unregister(String route) {
        routes.remove(route);
    }

    /** Open a GUI for a player by route name */
    public static void open(Player player, String route) {
        GuiDefinition definition = routes.get(route);
        if (definition == null) {
            throw new IllegalArgumentException("No GUI registered for route: " + route);
        }
        definition.open(player);
    }

    /** Check if a route exists */
    public static boolean exists(String route) {
        return routes.containsKey(route);
    }
}
