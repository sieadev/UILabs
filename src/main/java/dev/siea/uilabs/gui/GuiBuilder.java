package dev.siea.uilabs.gui;

import dev.siea.uilabs.element.Element;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class GuiBuilder {
    private final GuiDefinition definition;
    private final Map<Integer, Element> elements = new HashMap<>();
    private String name;

    public GuiBuilder(GuiDefinition definition) {
        this.definition = definition;
        this.elements.putAll(definition.getLayout());
        this.name = definition.getName();
    }

    /** Allows subclasses to customize/add elements before build */
    public void setElement(int slot, Element element) {
        elements.put(slot, element);
    }

    public void addElement(Element element) {
        for (int slot = 0; slot < definition.getSlots(); slot++) {
            if (!elements.containsKey(slot)) {
                elements.put(slot, element);
                return;
            }
        }
        throw new IllegalStateException("No free slots to add element");
    }

    public void setName(String name) {
        this.name = name;
    }

    /** Builds the actual GuiInstance for a player */
    public GuiInstance build(Player player) {
        Inventory inv = Bukkit.createInventory(player, definition.getSlots(), name);
        // Fill inventory
        for (Map.Entry<Integer, Element> entry : elements.entrySet()) {
            inv.setItem(entry.getKey(), entry.getValue().getItemstack());
        }
        return new GuiInstance(player, inv, elements);
    }

}
