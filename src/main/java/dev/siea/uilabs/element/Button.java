package dev.siea.uilabs.element;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Button extends ItemElement {
    public Button(Material material) {
        super(material);
    }

    public Button(Material material, String name) {
        super(material, name);
    }

    public Button(Material material, String name, List<String> lore) {
        super(material, name, lore);
    }

    public Button(Material material, String name, List<String> lore, int amount) {
        super(material, name, lore, amount);
    }

    public Button(ItemStack itemStack) {
        super(itemStack);
    }

    public void onButtonPressed(InventoryClickEvent e) {
        e.getWhoClicked().sendMessage(ChatColor.GREEN + "This is a button");
    }
}
