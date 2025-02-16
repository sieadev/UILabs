package dev.siea.uilabs.element;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Button extends ItemElement {
    public Button(int slot) {
        super(slot);
    }

    public Button(int slot, Material material) {
        super(slot, material);
    }

    public Button(int slot, Material material, String name) {
        super(slot, material, name);
    }

    public Button(int slot, Material material, String name, List<String> lore) {
        super(slot, material, name, lore);
    }

    public Button(int slot, Material material, String name, List<String> lore, int amount) {
        super(slot, material, name, lore, amount);
    }

    public Button(int slot, ItemStack itemStack) {
        super(slot, itemStack);
    }

    public void onButtonPressed(InventoryClickEvent e) {
        e.getWhoClicked().sendMessage(ChatColor.GREEN + "This is a button");
    }
}
