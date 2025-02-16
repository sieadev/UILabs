package dev.siea.uilabs;

import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class UILabs {
    private final Plugin plugin;
    private final List<AbstractInventoryGui> guis = new ArrayList<>();

    public UILabs(Plugin plugin) {
        this.plugin = plugin;
    }

    public GlobalInventoryGui createGlobalInventoryGui() {
        GlobalInventoryGui gui = new GlobalInventoryGui(plugin);
        guis.add(gui);
        return gui;
    }

    public PagedGlobalInventoryGui createPagedGlobalInventoryGui() {
        PagedGlobalInventoryGui gui = new PagedGlobalInventoryGui(plugin);
        guis.add(gui);
        return gui;
    }

    public PlayerInventoryGui createPlayerInventoryGui() {
        PlayerInventoryGui gui = new PlayerInventoryGui(plugin);
        guis.add(gui);
        return gui;
    }
}
