package dev.siea.uilabs.element;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.List;

public class Button extends ItemElement {
    public Button(Material material) {
        super(material);
    }

    public Button(Material material, Component name) {
        super(material, name);
    }

    public Button(Material material, Component name, List<Component> lore) {
        super(material, name, lore);
    }

    public Button(Material material, Component name, List<Component> lore, int amount) {
        super(material, name, lore, amount);
    }

    public Button(ItemStack itemStack) {
        super(itemStack);
    }

    public void onButtonPressed(InventoryClickEvent e) {
        e.getWhoClicked().sendMessage(Component.text("This is a button", NamedTextColor.GREEN));
    }

    @Override
    public Button clone() {
        return (Button) super.clone();
    }
}
