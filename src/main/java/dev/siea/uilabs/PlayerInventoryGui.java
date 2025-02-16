package dev.siea.uilabs;

import dev.siea.uilabs.frame.FrameView;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class PlayerInventoryGui extends AbstractInventoryGui {
    private final Map<Player, FrameView> playerFrames = new HashMap<>();

    public PlayerInventoryGui(Plugin holder) {
        super(holder);
    }
}