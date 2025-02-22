package dev.siea.uilabs.element;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import net.kyori.adventure.text.Component;

public class CheckBox extends Button {
    private boolean checked;

    public CheckBox(Material material, Component name) {
        super(material, name);
        this.checked = false;
    }

    @Override
    public void onButtonPressed(InventoryClickEvent e) {
        checked = !checked;
    }

    public boolean isChecked() {
        return checked;
    }

    @Override
    public CheckBox clone() {
        return (CheckBox) super.clone();
    }
}