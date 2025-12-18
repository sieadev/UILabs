package dev.siea.uilabs.session;

import dev.siea.uilabs.gui.GuiInstance;
import org.bukkit.entity.Player;

import java.util.ArrayDeque;
import java.util.Deque;

public class GuiSession {

    private final GuiSessionManager manager;
    private final Player player;
    private GuiInstance current;
    private final Deque<GuiInstance> history = new ArrayDeque<>();

    public GuiSession(GuiSessionManager manager, Player player) {
        this.manager = manager;
        this.player = player;
    }

    public GuiInstance getCurrent() {
        return current;
    }

    public void open(GuiInstance instance, boolean pushToHistory) {
        if (current != null && pushToHistory) {
            history.push(current);
        }
        current = instance;
        player.openInventory(instance.getInventory());
    }

    public boolean back() {
        if (!history.isEmpty()) {
            current = history.pop();
            player.openInventory(current.getInventory());
            return true;
        }
        return false;
    }

    public void close() {
        current = null;
        history.clear();
    }
}
