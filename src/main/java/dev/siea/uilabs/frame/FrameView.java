package dev.siea.uilabs.frame;

import dev.siea.uilabs.AbstractInventoryGui;
import dev.siea.uilabs.element.Element;
import dev.siea.uilabs.element.ItemElement;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class FrameView extends Frame {
    protected final AbstractInventoryGui parent;

    private final Inventory inventory;

    public FrameView(@NotNull AbstractInventoryGui parent) {
        this(parent, "New Frame");
    }

    public FrameView(@NotNull AbstractInventoryGui parent, String name) {
        this(parent, name, 9, 6);
    }

    public FrameView(@NotNull AbstractInventoryGui parent, String name, int width, int height) {
        this.parent = parent;
        if (height < 1) height = 1;
        if (height > 6) height = 6;
        if (width < 5) width = 5;
        if (width > 9) width = 9;
        if (width != 9 && height > 1) width = 9;
        this.inventory = Bukkit.createInventory(null, height * width, name);
    }

    @Override
    public final void addElement(@NotNull Element element) {
        elements.set(element.getSlot(), element);
        if (element instanceof ItemElement itemElement) {
            inventory.setItem(element.getSlot(), itemElement.getItemStack());
        }
    }

    @Override
    public final void removeElement(int slot) {
        elements.remove(slot);
        inventory.setItem(slot, null);
    }

    @Override
    public final void removeElement(@NotNull Element element) {
        elements.remove(element);
        inventory.setItem(element.getSlot(), null);
    }

    public void onClose(@NotNull InventoryCloseEvent event) {
        // Override this method to handle the event
    }

    public final AbstractInventoryGui getParent() {
        return parent;
    }

    public final Inventory getInventory() {
        return inventory;
    }
}
