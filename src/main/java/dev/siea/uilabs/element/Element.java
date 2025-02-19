package dev.siea.uilabs.element;

public class Element implements Cloneable {
    protected Priority priority;
    protected boolean fixedPosition;

    public Element() {
        this(Priority.NORMAL, false);
    }

    public Element(Priority priority) {
        this(priority, false);
    }

    public Element(boolean fixedPosition) {
        this(Priority.NORMAL, fixedPosition);
    }

    public Element(Priority priority, boolean fixedPosition) {
        this.priority = priority;
        this.fixedPosition = fixedPosition;
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

    public final boolean isFixedPosition() {
        return fixedPosition;
    }

    public final void setFixedPosition(boolean fixedPosition) {
        this.fixedPosition = fixedPosition;
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