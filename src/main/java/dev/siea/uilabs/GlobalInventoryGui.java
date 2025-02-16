package dev.siea.uilabs;

import dev.siea.uilabs.frame.Frame;
import dev.siea.uilabs.frame.FrameView;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class GlobalInventoryGui extends AbstractInventoryGui {
    public GlobalInventoryGui(Plugin holder) {
        this(holder, true);
    }

    public GlobalInventoryGui(Plugin holder, boolean allowClose) {
        super(holder, allowClose);
        this.allowClose = allowClose;
        InventoryGuiListener listener = new InventoryGuiListener();
        holder.getServer().getPluginManager().registerEvents(listener, holder);
    }

    @Override
    public FrameView createFrame() {
        FrameView inventoryFrame = new FrameView(this);
        addFrame(inventoryFrame);
        return inventoryFrame;
    }

    @Override
    public FrameView createFrame(String name) {
        FrameView inventoryFrame = new FrameView(this, name);
        addFrame(inventoryFrame);
        return inventoryFrame;
    }

    @Override
    public FrameView createFrame(String name, int width, int height) {
        FrameView inventoryFrame = new FrameView(this, name, width, height);
        addFrame(inventoryFrame);
        return inventoryFrame;
    }

    public void open(@NotNull Player player) {
        player.openInventory(((FrameView) inventoryFrames.getFirst()).getInventory());
    }

    public void open(@NotNull Player player, int index) {
        player.openInventory(((FrameView) inventoryFrames.get(index)).getInventory());
    }

    public final Plugin getHolder() {
        return holder;
    }

    final void addFrame(FrameView inventoryFrame) {
        inventoryFrames.add(inventoryFrame);
    }

    protected class InventoryGuiListener implements Listener {
        public InventoryGuiListener() {

        }

        @EventHandler
        public void onInventoryClick(InventoryClickEvent e) {
            for (Frame inventoryFrame : inventoryFrames) {
                if (e.getInventory().equals(((FrameView) inventoryFrame).getInventory())) {
                    e.setCancelled(true);
                    return;
                }
            }
        }

        @EventHandler
        public void onInventoryClose(InventoryCloseEvent e) {
            for (Frame inventoryFrame : inventoryFrames) {
                if (e.getInventory().equals(((FrameView) inventoryFrame).getInventory())) {
                    if (!allowClose) e.getPlayer().openInventory(((FrameView) inventoryFrame).getInventory());

                    break;
                }
            }
        }
    }
}
