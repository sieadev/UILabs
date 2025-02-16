package dev.siea.uilabs.element;

public class Element {
    protected Priority priority;

    public Element() {
        this(Priority.NORMAL);
    }

    public Element(Priority priority) {
        this.priority = priority;
    }

    public final Priority getPriority() {
        return priority;
    }

    public final void setPriority(Priority priority) {
        this.priority = priority;
    }

    public final void setPriority(int priority) {
        this.priority = Priority.values()[priority];
    }

    public enum Priority {
        BACKGROUND,
        LOWEST,
        LOW,
        NORMAL,
        HIGH,
        HIGHEST;
    }
}