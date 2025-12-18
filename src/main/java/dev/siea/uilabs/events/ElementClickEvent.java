package dev.siea.uilabs.events;

import dev.siea.uilabs.element.ClickableElement;
import dev.siea.uilabs.router.GuiRouter;
import org.bukkit.entity.Player;

public class ElementClickEvent {
    private final ClickableElement element;
    private final Player player;

    public ElementClickEvent(ClickableElement element, Player player){
        this.element = element;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public ClickableElement getElement() {
        return element;
    }

    public void route(String route) {
        GuiRouter.open(player, route);
    }
}
