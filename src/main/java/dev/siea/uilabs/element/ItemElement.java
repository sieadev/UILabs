package dev.siea.uilabs.element;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemElement extends Element {
    private ItemStack itemStack;

    public ItemElement(int slot) {
        this(slot, Material.BARRIER);
    }

    public ItemElement(int slot, @NotNull Material material) {
        this(slot, material, null);
    }

    public ItemElement(int slot, @NotNull Material material, String name) {
        this(slot, material, name, null);
    }

    public ItemElement(int slot, @NotNull Material material, String name, List<String> lore) {
        this(slot, material, name, lore, 1);
    }

    public ItemElement(int slot, @NotNull Material material, String name, List<String> lore, int amount) {
        super(slot);
        this.itemStack = createItem(material, name, lore, amount);
    }

    public ItemElement(int slot, ItemStack itemStack) {
        super(slot);
        this.itemStack = itemStack;
    }

    public final ItemStack getItemStack() {
        return itemStack;
    }

    public final void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    protected ItemStack createItem(@NotNull Material material, @Nullable String name, @Nullable List<String> lore, int amount) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        if (name != null) meta.setDisplayName(name);
        if (lore != null) meta.setLore(lore);
        return item;
    }
}