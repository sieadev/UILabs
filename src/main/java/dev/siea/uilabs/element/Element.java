package dev.siea.uilabs.element;

public class Element {
    protected int slot;

    public Element(int slot) {
        this.slot = slot;
    }

    public final int getSlot() {
        return slot;
    }

    public final void setSlot(int slot) {
        this.slot = slot;
    }
}