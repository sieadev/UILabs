package dev.siea.uilabs.gui;

import dev.siea.uilabs.UILabs;
import dev.siea.uilabs.element.Button;
import dev.siea.uilabs.frame.Border;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.*;

public class PagedInventoryGui extends AbstractInventoryGui {
    private final Map<Integer, DefaultInventoryGui> pages = new HashMap<>();

    public PagedInventoryGui(UILabs parent, Component name, int height, int width) {
        super(parent, name, height, width);
    }

    public DefaultInventoryGui create() {
        DefaultInventoryGui page = new DefaultInventoryGui(parent, name, height, width);
        pages.put(pages.size(), page);
        updatePaginationButton();
        return page;
    }

    public final DefaultInventoryGui getPage(int page) {
        return pages.get(page);
    }

    @Override
    public final void view(Player player) {
        view(player, 1);
    }

    @Override
    public final void view(Player... players) {
        for (Player player : players) {
            view(player);
        }
    }

    @Override
    public void closeAll() {
        for (DefaultInventoryGui page : pages.values()) {
            page.closeAll();
        }
    }

    public final void view(Player player, int page) {
        if (page < 1 || page > pages.size()) {
            throw new IllegalArgumentException("Invalid page number");
        }
        pages.get(page-1).view(player);
    }

    @Override
    public final void setBorder(Border border) {
        for (DefaultInventoryGui page : pages.values()) {
            page.setBorder(border);
        }
    }

    private void updatePaginationButton() {
        int inventorySize = width * height;
        for (int i = 0; i < pages.size(); i++) {
            DefaultInventoryGui page = pages.get(i);
            if (i > 0) {
                page.addElement(new BackwardPaginationButton(i + 1), BackwardPaginationButton.calculateSlot(inventorySize));
            }
            if (i < pages.size() - 1) {
                page.addElement(new ForwardPaginationButton(i + 1), ForwardPaginationButton.calculateSlot(inventorySize));
            }
        }
    }

    private final class BackwardPaginationButton extends Button {
        private final int page;

        private BackwardPaginationButton(int page) {
            super(Material.SPECTRAL_ARROW, Component.text("Previous Page", NamedTextColor.YELLOW), Collections.singletonList(Component.text("Click to go to the previous page (" + page + "/" + pages.size() + ")", NamedTextColor.GRAY)));
            this.page = page;
        }

        @Override
        public void onButtonPressed(InventoryClickEvent e) {
            view((Player) e.getWhoClicked(), page - 1);
        }

        private static int calculateSlot(int inventorySize) {
            if (inventorySize < 9) {
                return inventorySize / 2;
            } else {
                return inventorySize - 5;
            }
        }
    }

    private final class ForwardPaginationButton extends Button {
        private final int page;

        private ForwardPaginationButton(int page) {
            super(Material.SPECTRAL_ARROW, Component.text("Next Page", NamedTextColor.YELLOW), Collections.singletonList(Component.text("Click to go to the next page (" + page + "/" + pages.size() + ")", NamedTextColor.GRAY)));
            this.page = page;
        }

        @Override
        public void onButtonPressed(InventoryClickEvent e) {
            view((Player) e.getWhoClicked(), page + 1);
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
