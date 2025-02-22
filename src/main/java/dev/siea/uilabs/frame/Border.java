package dev.siea.uilabs.frame;

import dev.siea.uilabs.element.Element;
import dev.siea.uilabs.element.ItemElement;
import net.kyori.adventure.text.Component;
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
            ItemElement itemElement = new ItemElement(material[i], Component.space());
            itemElement.setPriority(1);
            borderParts[i] = itemElement;
        }
        this.borderParts = borderParts;
    }

    private Border(ItemStack... itemStacks) {
        Element[] borderParts = new Element[itemStacks.length];
        for (int i = 0; i < itemStacks.length; i++) {
            borderParts[i] = new ItemElement(itemStacks[i]);
        }
        this.borderParts = borderParts;
    }

    private Border(Element... borderParts) {
        for (Element element : borderParts) {
            element.setPriority(1);
        }
        this.borderParts = borderParts;
    }

    Map<Integer, Element> generateBorder(int height) {
        Map<Integer, Element> border = new HashMap<>();
        if (height < 3) {
            return border;
        }
        int borderIndex = 0;
        for (Integer slot : getBorderSlots(height)) {
            Element element = borderParts[borderIndex].clone();
            border.put(slot, element);
            borderIndex++;
            if (borderIndex >= borderParts.length) {
                borderIndex = 0;
            }
        }
        return border;
    }

    private static List<Integer> getBorderSlots(int rows) {
        List<Integer> result = new ArrayList<>();
        for (int c = 0; c < 9; c++) {
            result.add(c);
        }
        for (int r = 1; r < rows - 1; r++) {
            result.add(r * 9 + (9 - 1));
        }
        if (rows > 1) {
            for (int c = 9 - 1; c >= 0; c--) {
                result.add((rows - 1) * 9 + c);
            }
        }
        for (int r = rows - 2; r > 0; r--) {
            result.add(r * 9);
        }
        return result;
    }
}
