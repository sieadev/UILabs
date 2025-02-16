package dev.siea.uilabs.gui;

import dev.siea.uilabs.UILabs;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;


abstract class AbstractInventoryGui implements InventoryGui {
    protected final UILabs parent;
    protected final String name;
    protected final int width;
    protected final int height;
    private boolean allowClose = true;

    public AbstractInventoryGui(UILabs parent, String name, int width, int height){
        this.name = name;
        this.parent = parent;
        this.width = width;
        this.height = height;
    }

    public void onInventoryClick(InventoryClickEvent e) {
        // Override this method to handle inventory click events
    }

    public void onInventoryClose(InventoryCloseEvent e) {
        if (!allowClose) {
            e.getPlayer().openInventory(e.getInventory());
        }
    }

    @Override
    public void setAllowClose(boolean allowClose) {
        this.allowClose = allowClose;
    }


    @Override
    public final UILabs getParent() {
        return parent;
    }

    private final class InventoryListener implements Listener {
        // Implement the listener
    }
}
