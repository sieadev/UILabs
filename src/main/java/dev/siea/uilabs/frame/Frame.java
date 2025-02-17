package dev.siea.uilabs.frame;

import dev.siea.uilabs.element.Button;
import dev.siea.uilabs.element.Element;
import dev.siea.uilabs.element.ItemElement;
import dev.siea.uilabs.gui.InventoryGui;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Frame {
    private final InventoryGui parent;
    private final String name;
    private final int width;
    private final int height;
    private final List<Inventory> views = new ArrayList<>();
    private final Map<Integer, Element> elements = new HashMap<>();

    public Frame(InventoryGui parent, String name, int width, int height) {
        this.parent = parent;
        if (height < 1) height = 1;
        if (height > 6) height = 6;
        if (width < 5) width = 5;
        if (width > 9) width = 9;
        if (width != 9 && height > 1) width = 9;
        this.name = name;
        this.width = width;
        this.height = height;

        Bukkit.getPluginManager().registerEvents(new InventoryLister(), parent.getParent().getPlugin());
    }

    public Inventory getView() {
        return getView(name);
    }

    public Inventory getView(String name) {
        Inventory inventory = Bukkit.createInventory(null, height * width, name);
        for (Integer slot : elements.keySet()) {
            Element element = elements.get(slot);
            if (element instanceof ItemElement itemElement) {
                inventory.setItem(slot, itemElement.getItemStack());
            }
        }
        views.add(inventory);
        return inventory;
    }

    public void addElement(@NotNull Element element) {
        for (int i = 0; i < width * height; i++) {
            if (elements.get(i) == null) {
                addElement(element, i);
                return;
            }
        }
        throw new IllegalStateException("No available slots");
    }

    public void addElement(@NotNull Element element, int slot) {
        Element oldElement = elements.get(slot);
        if (oldElement != null && oldElement.getPriority().compareTo(element.getPriority()) > 0) {
            return;
        }
        elements.put(slot, element);
        updateViews(slot, element);
    }

    public void setBorder(Border border) {
        Map<Integer, Element> borderElements = border.generateBorder(height);
        for (Map.Entry<Integer, Element> entry : borderElements.entrySet()) {
            addElement(entry.getValue(), entry.getKey());
        }
    }

    public void removeElement(int slot) {
        elements.remove(slot);
        updateViews(slot, null);
    }

    public void removeElement(@NotNull Element element) {
        for (Map.Entry<Integer, Element> entry : elements.entrySet()) {
            if (entry.getValue().equals(element)) {
                elements.remove(entry.getKey());
                updateViews(entry.getKey(), null);
                return;
            }
        }
    }

    public Element getElement(int slot) {
        return elements.get(slot);
    }

    public List<Element> getElements() {
        return new ArrayList<>(elements.values());
    }

    public Map<Integer, Element> getLocatedElements() {
        return new HashMap<>(elements);
    }

    public InventoryGui getParent() {
        return parent;
    }

    private void updateViews(int slot, Element element) {
        if (element == null) {
            for (Inventory view : views) {
                view.setItem(slot, null);
            }
        } else if (element instanceof ItemElement itemElement) {
            for (Inventory view : views) {
                view.setItem(slot, itemElement.getItemStack());
            }
        }
    }

    private final class InventoryLister implements Listener {
        @EventHandler
        public void onInventoryClick(InventoryClickEvent e) {
            for (Inventory view : views) {
                if (e.getInventory().equals(view)) {
                    e.setCancelled(true);
                    Element element = elements.get(e.getSlot());
                    if (element instanceof Button button) {
                        button.onButtonPressed(e);
                    }
                    break;
                }
            }
        }
    }
}
