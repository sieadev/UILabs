package dev.siea.uilabs.listeners;

import dev.siea.uilabs.element.ClickableElement;
import dev.siea.uilabs.element.Element;
import dev.siea.uilabs.events.ElementClickEvent;
import dev.siea.uilabs.gui.GuiInstance;
import dev.siea.uilabs.session.GuiSession;
import dev.siea.uilabs.session.GuiSessionManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GuiListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        GuiSession session = GuiSessionManager.get(player);
        GuiInstance instance = session.getCurrent();
        if (instance == null) return;

        if (!event.getInventory().equals(instance.getInventory())) return;

        event.setCancelled(true);

        int slot = event.getSlot();
        Element element = instance.getElements().get(slot);
        if (element instanceof ClickableElement clickable) {
            clickable.onElementClick(new ElementClickEvent(clickable, player));
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player player)) return;
        GuiSessionManager.remove(player);
    }
}
