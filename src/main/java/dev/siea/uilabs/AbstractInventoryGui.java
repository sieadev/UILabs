package dev.siea.uilabs;

import dev.siea.uilabs.frame.Frame;
import dev.siea.uilabs.frame.FrameView;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractInventoryGui {
    protected final Plugin holder;
    protected final List<Frame> inventoryFrames = new ArrayList<>();
    protected boolean allowClose;

    public AbstractInventoryGui(Plugin holder) {
        this(holder, true);
    }

    public AbstractInventoryGui(Plugin holder, boolean allowClose) {
        this.holder = holder;
        this.allowClose = allowClose;
    }

    public Frame createFrame() {
        FrameView inventoryFrame = new FrameView(this);
        inventoryFrames.add(inventoryFrame);
        return inventoryFrame;
    }

    public Frame createFrame(String name) {
        FrameView inventoryFrame = new FrameView(this, name);
        inventoryFrames.add(inventoryFrame);
        return inventoryFrame;
    }

    public Frame createFrame(String name, int width, int height) {
        FrameView inventoryFrame = new FrameView(this, name, width, height);
        inventoryFrames.add(inventoryFrame);
        return inventoryFrame;
    }

    public final void setAllowClose(boolean allowClose) {
        this.allowClose = allowClose;
    }

    public Plugin getHolder() {
        return holder;
    }
}