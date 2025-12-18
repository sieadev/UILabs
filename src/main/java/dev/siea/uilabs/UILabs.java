package dev.siea.uilabs;

import dev.siea.uilabs.listeners.ConnectionListener;
import dev.siea.uilabs.listeners.GuiListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class UILabs {
    private static boolean loaded = false;
    private static ConnectionListener connectionListener;
    private static GuiListener guiListener;

    private UILabs() {}

    /**
     * Registers the UILabs API listeners for a plugin.
     * If already loaded, does nothing.
     *
     * @param plugin the plugin that wants to use UILabs
     */
    public static void register(Plugin plugin) {
        if (loaded) return;

        connectionListener = new ConnectionListener();
        guiListener = new GuiListener();

        Bukkit.getPluginManager().registerEvents(connectionListener, plugin);
        Bukkit.getPluginManager().registerEvents(guiListener, plugin);

        loaded = true;
    }

    /**
     * Returns true if UILabs has already been loaded/registered.
     */
    public static boolean isLoaded() {
        return loaded;
    }

    /**
     * Optionally expose the listeners if needed.
     */
    public static ConnectionListener getConnectionListener() {
        return connectionListener;
    }

    public static GuiListener getGuiListener() {
        return guiListener;
    }
}
