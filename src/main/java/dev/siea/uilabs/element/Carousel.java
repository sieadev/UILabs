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
    private final List<ItemElement> elements = new ArrayList<>();
    private final String name;
    private final List<String> lore = new ArrayList<>();
    private int speed = 5;
    private int currentIndex = 0;

    public Carousel(int slot) {
        super(slot);
        name = null;
    }

    public Carousel(int slot, ItemElement... itemElements) {
        super(slot);
        name = null;
        Collections.addAll(elements, itemElements);
    }

    public Carousel(int slot, ItemStack... itemStacks) {
        super(slot);
        name = null;
        for (ItemStack itemStack : itemStacks) {
            elements.add(createItemElement(itemStack));
        }
    }

    public Carousel(int slot, String name, Material... material) {
        super(slot);
        this.name = name;
        for (int i = 1; i < material.length; i++) {
            elements.add(createItemElement(material[i], name, Collections.singletonList("")));
        }
    }

    public Carousel(int slot, String name, String lore, Material... material) {
        super(slot);
        this.name = name;
        this.lore.add(lore);
        for (int i = 1; i < material.length; i++) {
            elements.add(createItemElement(material[i], name, Collections.singletonList(lore)));
        }
    }

    public void addItem(ItemElement itemElement) {
        elements.add(itemElement);
    }

    public void addItem(ItemStack itemStack) {
        elements.add(createItemElement(itemStack));
    }

    public void addItem(Material material) {
        elements.add(createItemElement(material, name, lore));
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public ItemElement next() {
        currentIndex++;
        if (currentIndex >= elements.size()) {
            currentIndex = 0;
        }
        return elements.get(currentIndex);
    }

    protected ItemElement createItemElement(@NotNull Material material, @Nullable String name, @Nullable List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        if (name != null) meta.setDisplayName(name);
        if (lore != null) meta.setLore(lore);
        return createItemElement(item);
    }

    protected ItemElement createItemElement(ItemStack itemStack) {
        return new ItemElement(slot, itemStack);
    }
}
