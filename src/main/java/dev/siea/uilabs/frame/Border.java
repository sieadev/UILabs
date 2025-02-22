package dev.siea.uilabs.frame;

import dev.siea.uilabs.element.Element;
import dev.siea.uilabs.element.ItemElement;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Border {
    private final Element[] borderParts;

    public static Border of(Material... materials) {
        return new Border(materials);
    }

    public static Border of(ItemStack... itemStacks) {
        return new Border(itemStacks);
    }

    public static Border of(Element... elements) {
        return new Border(elements);
    }

    private Border(Material... material) {
        Element[] borderParts = new Element[material.length];
        for (int i = 0; i < material.length; i++) {
            ItemElement itemElement = new ItemElement(material[i], " ");
            itemElement.setPriority(1);
            itemElement.setFixedPosition(true);
            borderParts[i] = itemElement;
        }
        this.borderParts = borderParts;
    }

    private Border(ItemStack... itemStacks) {
        Element[] borderParts = new Element[itemStacks.length];
        for (int i = 0; i < itemStacks.length; i++) {
            ItemElement itemElement = new ItemElement(itemStacks[i]);
            itemElement.setPriority(1);
            itemElement.setFixedPosition(true);
            borderParts[i] = itemElement;
        }
        this.borderParts = borderParts;
    }

    private Border(Element... borderParts) {
        for (Element element : borderParts) {
            element.setPriority(1);
            element.setFixedPosition(true);
        }
        this.borderParts = borderParts;
    }

    Map<Integer, Element> generateBorder(int height, int width) {
        Map<Integer, Element> border = new HashMap<>();
        if (height < 3 || width < 3) {
            return border;
        }
        int borderIndex = 0;
        for (Integer slot : getBorderSlots(height, width)) {
            Element element = borderParts[borderIndex].clone();
            border.put(slot, element);
            borderIndex++;
            if (borderIndex >= borderParts.length) {
                borderIndex = 0;
            }
        }
        return border;
    }

    private static List<Integer> getBorderSlots(int rows, int columns) {
        List<Integer> result = new ArrayList<>();
        for (int c = 0; c < columns; c++) {
            result.add(c);
        }
        for (int r = 1; r < rows - 1; r++) {
            result.add(r * columns + (columns - 1));
        }
        if (rows > 1) {
            for (int c = columns - 1; c >= 0; c--) {
                result.add((rows - 1) * columns + c);
            }
        }
        for (int r = rows - 2; r > 0; r--) {
            result.add(r * columns);
        }
        return result;
    }
}
