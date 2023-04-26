package dev.xernas.tpbow.events;

import dev.xernas.tpbow.TPBow;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ArrowLandEvent implements Listener {

    Map<Projectile, String> arrows = new HashMap<Projectile, String>();

    @EventHandler
    public void onArrowLand(ProjectileHitEvent e) {
        if (Objects.equals(arrows.get(e.getEntity()), "tparrow")) {
            Block hit = e.getHitBlock();
            Entity entity = e.getHitEntity();
            Player player = (Player) e.getEntity().getShooter();
            if (entity != null) {
                player.teleport(new Location(entity.getLocation().getWorld(), entity.getLocation().getX(), entity.getLocation().getY(), entity.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch()));
            }
            if (hit != null) {
                player.teleport(new Location(hit.getLocation().getWorld(), hit.getLocation().getX(), hit.getLocation().getY() + 1, hit.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch()));
            }
        }
    }

    @EventHandler
    public void onArrowShoot(ProjectileLaunchEvent e) {
        Player player = (Player) e.getEntity().getShooter();
        ItemStack itemInHand = player.getItemInUse();
        if (itemInHand.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(TPBow.getInstance(), "TPBow"), PersistentDataType.STRING)) {
            arrows.put(e.getEntity(), "tparrow");
        }
    }

}
