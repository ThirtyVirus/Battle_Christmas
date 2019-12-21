package thirtyvirus.template.events.misc;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import thirtyvirus.multiversion.Sound;
import thirtyvirus.multiversion.XMaterial;
import thirtyvirus.template.BattleChristmas;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SnowImpact implements Listener {

    private BattleChristmas main = null;
    public SnowImpact(BattleChristmas main) {
        this.main = main;
    }

    public static final List<Material> EXCLUDE = Arrays.asList(Material.TNT, Material.LONG_GRASS, Material.DOUBLE_PLANT,
            Material.AIR, Material.YELLOW_FLOWER, Material.RED_ROSE);

    // doesn't break these blocks
    public static final List<Material> EXCLUDE_PLUS = Arrays.asList(Material.WOOL, Material.COAL_BLOCK, Material.PACKED_ICE, Material.CLAY);

    @EventHandler
    public void onImpact(ProjectileHitEvent event) {

        switch (event.getEntity().getType()) {
            case ARROW:
                event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 20.0F);
                break;
            case EGG:
                event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.SNOWMAN);
                break;
            case SNOWBALL:
                event.getEntity().getWorld().getBlockAt(event.getEntity().getLocation()).setType(Material.ICE);
        }
    }
    @EventHandler
    public void onExplosion(EntityExplodeEvent event) {

        List<Block> destroyed = event.blockList();
        Iterator<Block> it = destroyed.iterator();
        while (it.hasNext()) {
            Block block = it.next();
            if (EXCLUDE.contains(block.getType())) continue;
            if (EXCLUDE_PLUS.contains(block.getType())) { it.remove(); continue; }

            switch(block.getType()) {
                case LOG:
                case LOG_2:
                    block.setType(Material.WOOL);
                    block.setData((byte)14);
                    break;
                case LEAVES:
                case LEAVES_2:
                    block.setType(Material.WOOL);
                    block.setData((byte)3);
                    break;
                case STONE:
                    block.setType(Material.PACKED_ICE);
                    break;
                case DIAMOND_ORE:
                case COAL_ORE:
                case EMERALD_ORE:
                case GOLD_ORE:
                case IRON_ORE:
                case REDSTONE_ORE:
                    block.setType(Material.COAL_BLOCK);
                    break;
                case WOOD:
                    block.setData((byte)5);
                    break;
                case SAND:
                    block.setType(Material.CLAY);
                    break;
                default:
                    block.setType(Material.SNOW_BLOCK);
                    break;
            }
            it.remove();
        }


        // spawn more TNT
        //for (int counter = 0; counter < 2; counter++) {
        //    FallingBlock block = event.getEntity().getWorld().spawnFallingBlock(event.getEntity().getLocation(), Material.TNT, (byte) 0);
        //    float x = (float) -1 + (float) (Math.random() * ((1 - -1) + 1));
        //   float y = (float) -5 + (float)(Math.random() * ((5 - -5) + 1));
        //    float z = (float) -0.3 + (float)(Math.random() * ((0.3 - -0.3) + 1));
        //    double multiplier = 2;
        //    block.setVelocity(new Vector(x * multiplier, y * multiplier, z * multiplier));
        //}
    }

    @EventHandler
    public void onEntityExplode(ExplosionPrimeEvent event){
        if(event.getEntity().getType() == EntityType.PRIMED_TNT){
            event.setRadius(10.0F);
        }
    }

}
