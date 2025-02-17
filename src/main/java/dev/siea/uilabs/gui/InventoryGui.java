package dev.siea.uilabs.gui;

import dev.siea.uilabs.UILabs;
import dev.siea.uilabs.frame.Border;
import org.bukkit.entity.Player;

public interface InventoryGui {
    void view(Player player);
    void setBorder(Border border);
    void setAllowClose(boolean allowClose);
    void closeAll();
    public UILabs getParent();
}
