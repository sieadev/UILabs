package dev.siea.uilabs.frame;

import dev.siea.uilabs.element.Button;
import dev.siea.uilabs.element.Carousel;
import dev.siea.uilabs.element.Element;
import dev.siea.uilabs.element.ItemElement;
import dev.siea.uilabs.gui.InventoryGui;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;
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
    private final CarouselTimer carouselTimer = new CarouselTimer();

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
        carouselTimer.updateCarousels();
        return inventory;
    }

    public List<HumanEntity> getViewers() {
        List<HumanEntity> viewers = new ArrayList<>();
        for (Inventory view : views) {
            viewers.addAll(view.getViewers());
        }
        return viewers;
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
        carouselTimer.updateCarousels();
    }

    public void setBorder(Border border) {
        Map<Integer, Element> borderElements = border.generateBorder(height, width);
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

    public void removeAll() {
        for (Map.Entry<Integer, Element> entry : elements.entrySet()) {
            elements.remove(entry.getKey());
            updateViews(entry.getKey(), null);
            return;
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
                    } else if (element instanceof Carousel carousel) {
                        Element carouselElement = carousel.getCurrent();
                        if (carouselElement instanceof Button carouselButton) {
                            carouselButton.onButtonPressed(e);
                        }
                    }
                    break;
                }
            }
        }
    }

    private final class CarouselTimer extends BukkitRunnable {
        private final Map<Integer, Carousel> carousels = new HashMap<>();
        private int tickCount = 0;
        private boolean isRunning = false;

        public CarouselTimer() {
            updateCarousels();
        }

        @Override
        public void run() {
            if (carousels.isEmpty()) {
                cancel();
                return;
            }
            tickCount++;
            for (Map.Entry<Integer, Carousel> entry : carousels.entrySet()) {
                Carousel carousel = entry.getValue();
                Integer slot = entry.getKey();
                if (tickCount % carousel.getSpeed() == 0) {
                    ItemElement nextElement = carousel.next();
                    for (Inventory view : views) {
                        if (view.getViewers().isEmpty()) { continue; }
                        view.setItem(slot, nextElement.getItemStack());
                    }
                }
            }
        }

        private void start() {
            if (!isRunning) {
                this.runTaskTimer(parent.getParent().getPlugin(), 0L, 1L);
                isRunning = true;
            }
        }

        public void cancel() {
            if (isRunning) {
                super.cancel();
                isRunning = false;
            }
        }

        public void updateCarousels() {
            carousels.clear();
            for (Map.Entry<Integer, Element> entry : elements.entrySet()) {
                Element element = entry.getValue();
                if (element instanceof Carousel carousel) {
                    carousels.put(entry.getKey(),carousel);
                }
            }
            if (carousels.isEmpty()) {
                cancel();
            } else {
                start();
            }
        }
    }
}
