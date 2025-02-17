package dev.siea.uilabs;

import dev.siea.uilabs.frame.Border;
import dev.siea.uilabs.gui.DefaultInventoryGui;
import dev.siea.uilabs.gui.InventoryGui;
import dev.siea.uilabs.gui.PagedInventoryGui;
import org.bukkit.plugin.Plugin;

public class UILabs {
    private final Plugin plugin;
    private boolean allowCloseDefault = true;
    private Border defaultBorder;

    public UILabs(Plugin plugin) {
        this.plugin = plugin;
    }

    public DefaultInventoryGui create(String name) {
        return create(name, 9, 6);
    }

    public DefaultInventoryGui create(String name, int width, int height) {
        DefaultInventoryGui inventoryGui = new DefaultInventoryGui(this, name, width, height);
        if (defaultBorder != null) inventoryGui.setBorder(defaultBorder);
        inventoryGui.setAllowClose(allowCloseDefault);
        return inventoryGui;
    }

    public PagedInventoryGui createPaged(String name) {
        return createPaged(name, 9, 6);
    }

    public PagedInventoryGui createPaged(String name, int width, int height) {
        PagedInventoryGui inventoryGui = new PagedInventoryGui(this, name, width, height);
        if (defaultBorder != null) inventoryGui.setBorder(defaultBorder);
        inventoryGui.setAllowClose(allowCloseDefault);
        return inventoryGui;
    }

    public void registerCustomGui(InventoryGui gui) {

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
}
