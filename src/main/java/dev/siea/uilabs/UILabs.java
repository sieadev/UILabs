package dev.siea.uilabs;

import dev.siea.uilabs.frame.Border;
import dev.siea.uilabs.gui.DefaultInventoryGui;
import dev.siea.uilabs.gui.InventoryGui;
import dev.siea.uilabs.gui.PagedInventoryGui;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class UILabs {
    private final Plugin plugin;
    private final List<InventoryGui> registry = new ArrayList<>();
    private boolean allowCloseDefault = true;
    private Border defaultBorder;

    public UILabs(Plugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(new LifecycleListener(), plugin);
    }

    public DefaultInventoryGui create(Component name) {
        return create(name, 6, 9);
    }

    public DefaultInventoryGui create(Component name, int height, int width) {
        DefaultInventoryGui inventoryGui = new DefaultInventoryGui(this, name, height, width);
        if (defaultBorder != null) inventoryGui.setBorder(defaultBorder);
        inventoryGui.setAllowClose(allowCloseDefault);
        registry.add(inventoryGui);
        return inventoryGui;
    }

    public PagedInventoryGui createPaged(Component name) {
        return createPaged(name, 9, 6);
    }

    public PagedInventoryGui createPaged(Component name, int height, int width) {
        PagedInventoryGui inventoryGui = new PagedInventoryGui(this, name, height, width);
        if (defaultBorder != null) inventoryGui.setBorder(defaultBorder);
        inventoryGui.setAllowClose(allowCloseDefault);
        registry.add(inventoryGui);
        return inventoryGui;
    }

    public void registerCustomGui(InventoryGui gui) {
        registry.add(gui);
    }

    public void setAllowCloseDefault(boolean allowClose) {
        this.allowCloseDefault = allowClose;
    }

    public void setDefaultBorder(Border border) {
        this.defaultBorder = border;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    private class LifecycleListener implements Listener {
        @EventHandler
        public void onDisable(PluginDisableEvent e) {
            for (InventoryGui gui : registry) {
                gui.closeAll();
            }
        }
    }
}
