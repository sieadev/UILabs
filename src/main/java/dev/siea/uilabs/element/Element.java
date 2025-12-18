package dev.siea.uilabs.element;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Element {

    private final ItemStack itemstack;

    public Element(Material material) {
        this(new ItemStack(material));
    }

    public Element(Material material, String name) {
        this(material, name, null);
    }

    public Element(Material material, String name, List<String> lore) {
        this(createItem(material, name, lore));
    }

    public Element(ItemStack item) {
        this.itemstack = Objects.requireNonNull(item, "ItemStack cannot be null").clone();
    }

    private static ItemStack createItem(Material material, String name, List<String> lore) {
        Objects.requireNonNull(material, "Material cannot be null");

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            if (name != null) {
                meta.setDisplayName(name);
            }

            if (lore != null && !lore.isEmpty()) {
                meta.setLore(new ArrayList<>(lore));
            }

            item.setItemMeta(meta);
        }

        return item;
    }

    /**
     * Returns a clone to protect internal state.
     */
    public ItemStack getItemstack() {
        return itemstack.clone();
    }
}
