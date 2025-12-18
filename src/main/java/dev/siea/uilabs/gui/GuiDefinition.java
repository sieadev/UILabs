package dev.siea.uilabs.gui;

import dev.siea.uilabs.element.Element;
import dev.siea.uilabs.exceptions.NoMoreSlotsException;
import dev.siea.uilabs.exceptions.SlotOutOfBoundsException;
import dev.siea.uilabs.session.GuiSessionManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiDefinition {
    private final Map<Integer, Element> elements;
    private String name;
    private int slots;

    public GuiDefinition(String name) {
        this(name, 54);
    }

    public GuiDefinition(String name, int wight, int height) {
        this(name, wight * height);
    }

    public GuiDefinition(String name, Integer slots) {
        elements = new HashMap<>();
        this.name = name;
        setSlots(slots);
    }

    public final int getSlots() {
        return slots;
    }

    public final void setSlots(int slots) {
        validateSlots(slots);
        this.slots = slots;

        elements.keySet().removeIf(slot -> slot >= slots);
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final Map<Integer, Element> getLayout() {
        return elements;
    }

    public final List<Element> getElements() {
        return elements.values().stream().toList();
    }

    public final Element getElement(int slot) {
        return elements.get(slot);
    }

    public final void setElement(int slot, Element element) {
        if (!(slot < slots)) {
            throw new SlotOutOfBoundsException(
                    "Slot index " + slot + " is out of bounds. Valid range is 0 to " + (slots - 1)
            );
        }
        elements.put(slot, element);
    }

    public final void addElement(Element element) {
        if (elements.size() == slots) {
            throw new NoMoreSlotsException(
                    "No free slots available. Maximum slots: " + slots
            );
        }

        for (int slot = 0; slot < slots; slot++) {
            if (!elements.containsKey(slot)) {
                elements.put(slot, element);
                return;
            }
        }
    }

    /** Hook for subclasses to customize the GUI before building */
    public void onBuild(GuiBuilder builder) {

    }

     /**
      * Opens the GUI for the player
      * */
    public final void open(Player player) {
        GuiBuilder builder = new GuiBuilder(this);
        onBuild(builder);

        GuiInstance instance = builder.build(player);
        player.openInventory(instance.getInventory());

        GuiSessionManager.register(player, instance);
    }

    private void validateSlots(int slots) {
        if (slots < 9 || slots > 54 || slots % 9 != 0) {
            throw new IllegalArgumentException(
                    "Invalid inventory size: " + slots + ". Must be between 9 and 54 and a multiple of 9."
            );
        }
    }
}
