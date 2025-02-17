package dev.siea.uilabs.element;

public class Element implements Cloneable {
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

    @Override
    public Element clone() {
        try {
            return (Element) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}