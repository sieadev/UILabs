package dev.siea.uilabs.frame;

import dev.siea.uilabs.element.Element;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    protected final List<Element> elements = new ArrayList<>();

    public void addElement(@NotNull Element element) {
        elements.set(element.getSlot(), element);
    }

    public void removeElement(int slot) {
        elements.remove(slot);
    }

    public void removeElement(@NotNull Element element) {
        elements.remove(element);
    }

    public final Element getElement(int slot) {
        return elements.get(slot);
    }

    public final List<Element> getElements() {
        return elements;
    }
}
