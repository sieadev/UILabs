package dev.siea.uilabs.frame;

import dev.siea.uilabs.AbstractInventoryGui;
import dev.siea.uilabs.GlobalInventoryGui;
import dev.siea.uilabs.element.Button;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Collections;

public class PagedFrameView extends FrameView {
    private int totalPages = 1;
    private int currentPage = 1;

    public PagedFrameView(AbstractInventoryGui parent) {
        super(parent);
    }

    public PagedFrameView(AbstractInventoryGui parent, String name) {
        super(parent, name);
    }

    public PagedFrameView(AbstractInventoryGui parent, String name, int width, int height) {
        super(parent, name, width, height);
    }

    public final void updatePaginationData(int totalPages, int currentPage) {
        this.totalPages = totalPages;
        this.currentPage = currentPage;

        if (currentPage > 1) {
            addElement(new BackwardPaginationButton(getInventory().getSize()));
        }
        if (currentPage < totalPages) {
            addElement(new ForwardPaginationButton(getInventory().getSize()));
        }
    }

    private class BackwardPaginationButton extends Button {

        private BackwardPaginationButton(int inventorySize) {
            super(calculateSlot(inventorySize), Material.SPECTRAL_ARROW, ChatColor.YELLOW + "Previous Page", Collections.singletonList(ChatColor.GRAY + "Click to go to the previous page (" + currentPage + "/" + totalPages + ")"));
        }

        @Override
        public void onButtonPressed(InventoryClickEvent e) {
            ((GlobalInventoryGui) getParent()).open((Player) e.getWhoClicked(), currentPage - 1);
        }

        private static int calculateSlot(int inventorySize) {
            if (inventorySize < 9) {
                return inventorySize / 2;
            } else {
                return inventorySize - 5;
            }
        }
    }

    private class ForwardPaginationButton extends Button {

        private ForwardPaginationButton(int inventorySize) {
            super(calculateSlot(inventorySize), Material.SPECTRAL_ARROW, ChatColor.YELLOW + "Next Page", Collections.singletonList(ChatColor.GRAY + "Click to go to the next page (" + currentPage + "/" + totalPages + ")"));
        }

        @Override
        public void onButtonPressed(InventoryClickEvent e) {
            ((GlobalInventoryGui) getParent()).open((Player) e.getWhoClicked(), currentPage + 1);
        }

        private static int calculateSlot(int inventorySize) {
            if (inventorySize < 9) {
                return inventorySize / 2 + 2;
            } else {
                return inventorySize - 3;
            }
        }
    }
}
