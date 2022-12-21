package me.weisboys.randomsurvival;

import io.netty.util.internal.ThreadLocalRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;


/**
 *
 * @author weisb
 */

public class MobDrops implements Listener{

     private Map<EntityType,Material> dropID = new HashMap<>();

     private ToggleCommand tcmd;
     public MobDrops (ToggleCommand tc) {
         tcmd = tc;
     }


    @EventHandler
    public void mobDrop (EntityDeathEvent e) {
        if (!tcmd.isEnabled()) {
            return;
        }
        EntityType entityType = e.getEntity().getType();

        if (entityType == EntityType.PLAYER) {
            return;
        }
        e.getDrops().clear();
        if (!dropID.containsKey(entityType)) {
            Random random = ThreadLocalRandom.current();
            dropID.put(entityType, Material.values()[random.nextInt(Material.values().length)]);
        }

        Material material = dropID.get(entityType);
        ItemStack droppedItem = new ItemStack(material);
        e.getDrops().add(droppedItem);
    }

    public void resetMap() {
        dropID.clear();
    }

    public Map<EntityType,Material> getDropID() {
        return dropID;
    }
}
