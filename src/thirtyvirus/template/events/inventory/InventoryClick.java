package thirtyvirus.template.events.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import thirtyvirus.template.BattleChristmas;
import thirtyvirus.template.helpers.Utilities;

import static thirtyvirus.template.helpers.ActionSound.CLICK;

public class InventoryClick implements Listener {

    private BattleChristmas main = null;
    public InventoryClick(BattleChristmas main) {
        this.main = main;
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

    }

}