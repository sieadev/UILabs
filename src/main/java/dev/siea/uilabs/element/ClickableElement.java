package dev.siea.uilabs.element;

import dev.siea.uilabs.events.ElementClickEvent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class ClickableElement extends Element {

    public ClickableElement(Material material) {
        super(material);
    }

    public ClickableElement(Material material, String name) {
        super(material, name);
    }

    public ClickableElement(Material material, String name, List<String> lore) {
        super(material, name, lore);
    }

    public ClickableElement(ItemStack item) {
        super(item);
    }

    public abstract void onElementClick(ElementClickEvent event);
}
