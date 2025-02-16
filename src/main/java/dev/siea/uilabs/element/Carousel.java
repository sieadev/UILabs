package dev.siea.uilabs.element;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carousel extends Element {
    private final List<ItemStack> itemStacks = new ArrayList<>();
    private final String name;
    private final List<String> lore = new ArrayList<>();
    private int speed = 5;
    private int currentIndex = 0;

    public Carousel(int slot) {
        super(slot);
        name = null;
    }

    public Carousel(int slot, ItemStack... itemStacks) {
        super(slot);
        name = null;
        Collections.addAll(this.itemStacks, itemStacks);
    }

    public Carousel(int slot, String name, Material... material) {
        super(slot);
        this.name = name;
        for (int i = 1; i < material.length; i++) {
            itemStacks.add(createItem(material[i], name, Collections.singletonList("")));
        }
    }

    public Carousel(int slot, String name, String lore, Material... material) {
        super(slot);
        this.name = name;
        this.lore.add(lore);
        for (int i = 1; i < material.length; i++) {
            itemStacks.add(createItem(material[i], name, Collections.singletonList(lore)));
        }
    }

    public void addItem(ItemStack itemStack) {
        itemStacks.add(itemStack);
    }

    public void addItem(Material material) {
        itemStacks.add(createItem(material, name, lore));
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public ItemStack next() {
        currentIndex++;
        if (currentIndex >= itemStacks.size()) {
            currentIndex = 0;
        }
        return itemStacks.get(currentIndex);
    }

    protected ItemStack createItem(@NotNull Material material, @Nullable String name, @Nullable List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        if (name != null) meta.setDisplayName(name);
        if (lore != null) meta.setLore(lore);
        return item;
    }
}
