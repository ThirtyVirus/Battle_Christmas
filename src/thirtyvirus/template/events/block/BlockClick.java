package thirtyvirus.template.events.block;

import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import thirtyvirus.multiversion.Sound;
import thirtyvirus.template.BattleChristmas;
import thirtyvirus.template.helpers.ActionSound;
import thirtyvirus.template.helpers.Utilities;

public class BlockClick implements Listener {

    private BattleChristmas main = null;
    public BlockClick(BattleChristmas main) {
        this.main = main;
    }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        switch (player.getInventory().getItemInHand().getType()) {
            case SNOW_BLOCK:
                player.launchProjectile(Snowball.class);
                Sound.SHOOT_ARROW.playSound(player);
                break;
            case MILK_BUCKET:
                player.launchProjectile(Egg.class);
                Sound.SHOOT_ARROW.playSound(player);
                break;
            case TNT:
                if (event.getAction() != Action.RIGHT_CLICK_AIR) return;
                for (int counter = 0; counter < 5; counter++) {
                    FallingBlock block = event.getPlayer().getWorld().spawnFallingBlock(event.getPlayer().getLocation(), Material.TNT, (byte) 0);
                    double x = player.getLocation().getDirection().getX() + (float) (Math.random() * ((1 - -1)) * 0.3);
                    double y = player.getLocation().getDirection().getY() + (float) (Math.random() * ((1 - -1)) * 0.3);
                    double z = player.getLocation().getDirection().getZ() + (float) (Math.random() * ((1 - -1)) * 0.3);
                    double multiplier = 2;
                    block.setVelocity(new Vector(x * multiplier, y * multiplier, z * multiplier));
                    Sound.SHOOT_ARROW.playSound(player);
                }
                break;
            case ARROW:
                player.launchProjectile(Arrow.class);
                Sound.SHOOT_ARROW.playSound(player);
                break;
            case MAGMA_CREAM:
                Entity ball = event.getPlayer().getWorld().spawnEntity(player.getLocation().add(0, 3, 0), EntityType.FIREBALL);
                double x = player.getLocation().getDirection().getX();
                double y = player.getLocation().getDirection().getY();
                double z = player.getLocation().getDirection().getZ();
                double multiplier = 2;
                ball.setVelocity(new Vector(x * multiplier, y * multiplier, z * multiplier));
                Sound.SHOOT_ARROW.playSound(player);
                break;
        }
    }

}