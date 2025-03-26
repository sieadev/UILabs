package dev.siea.uilabs.gui;

import dev.siea.uilabs.UILabs;
import dev.siea.uilabs.element.Element;
import dev.siea.uilabs.frame.Border;
import dev.siea.uilabs.frame.Frame;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public final class DefaultInventoryGui extends AbstractInventoryGui {
    private final Inventory view;
    private final Frame frame;

    public DefaultInventoryGui(UILabs parent,String name, int height, int width) {
        super(parent, name, height, width);
        this.frame = new Frame(this, name, width, height);
        this.view = frame.getView();
    }

    @Override
    public void view(Player player) {
        player.openInventory(view);
    }

    @Override
    public void view(Player... players) {
        for (Player player : players) {
            view(player);
        }
    }

    public List<HumanEntity> getViewers() {
        return frame.getViewers();
    }

    public void addElement(Element element) {
        frame.addElement(element);
    }

    public void addElement(Element element, int slot) {
        frame.addElement(element, slot);
    }

    public void removeElement(Element element) {
        frame.addElement(element);
    }

    public void removeAll() {
        frame.removeAll();
    }

    @Override
    public void setBorder(Border border) {
        frame.setBorder(border);
    }

    @Override
    public void closeAll() {
        new ArrayList<>(view.getViewers()).forEach(HumanEntity::closeInventory);
    }
}
