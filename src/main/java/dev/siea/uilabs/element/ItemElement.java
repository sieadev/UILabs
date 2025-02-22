package dev.siea.uilabs.element;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import net.kyori.adventure.text.Component;

import java.util.List;

public class ItemElement extends Element {
    private ItemStack itemStack;

    public ItemElement(@NotNull Material material) {
        this(material, null);
    }

    public ItemElement(@NotNull Material material, Component name) {
        this(material, name, null);
    }

    public ItemElement(@NotNull Material material, Component name, List<Component> lore) {
        this(material, name, lore, 1);
    }

    public ItemElement(@NotNull Material material, Component name, List<Component> lore, int amount) {
        this.itemStack = createItem(material, name, lore, amount);
    }

    public ItemElement(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public final ItemStack getItemStack() {
        return itemStack;
    }

    public final void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    protected ItemStack createItem(@NotNull Material material, @Nullable Component name, @Nullable List<Component> lore, int amount) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        if (name != null) meta.displayName(name);
        if (lore != null) meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public ItemElement clone() {
        return (ItemElement) super.clone();
    }
}