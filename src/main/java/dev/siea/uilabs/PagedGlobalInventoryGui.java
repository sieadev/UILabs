package dev.siea.uilabs;

import dev.siea.uilabs.frame.Frame;
import dev.siea.uilabs.frame.FrameView;
import dev.siea.uilabs.frame.PagedFrameView;
import org.bukkit.plugin.Plugin;

public class PagedGlobalInventoryGui extends GlobalInventoryGui {
    public PagedGlobalInventoryGui(Plugin holder) {
        super(holder);
    }

    public PagedGlobalInventoryGui(Plugin holder, boolean allowClose) {
        super(holder, allowClose);
    }

    @Override
    public PagedFrameView createFrame() {
        PagedFrameView frame = new PagedFrameView(this);
        addFrame(frame);
        reorganizePages();
        return frame;
    }

    @Override
    public PagedFrameView createFrame(String name) {
        PagedFrameView frame = new PagedFrameView(this, name);
        addFrame(frame);
        reorganizePages();
        return frame;
    }

    @Override
    public PagedFrameView createFrame(String name, int width, int height) {
        PagedFrameView frame = new PagedFrameView(this, name, width, height);
        addFrame(frame);
        reorganizePages();
        return frame;
    }

    private void reorganizePages() {
        for (Frame inventoryFrame : inventoryFrames) {
            ((PagedFrameView) inventoryFrame).updatePaginationData(inventoryFrames.size(), inventoryFrames.indexOf(inventoryFrame) + 1);
        }
    }
}
