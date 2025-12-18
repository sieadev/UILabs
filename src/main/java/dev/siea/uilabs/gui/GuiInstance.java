package dev.siea.uilabs.gui;

import dev.siea.uilabs.element.Element;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public class GuiInstance {

    private final Player player;
    private final Inventory inventory;
    private final Map<Integer, Element> elements;

    public GuiInstance(Player player, Inventory inventory, Map<Integer, Element> elements) {
        this.player = player;
        this.inventory = inventory;
        this.elements = elements;
    }

    public Player getPlayer() {
        return player;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Map<Integer, Element> getElements() {
        return elements;
    }
}
