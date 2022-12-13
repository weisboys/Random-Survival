package me.weisboys.randomsurvival;

import io.netty.util.internal.ThreadLocalRandom;
import java.util.Random;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.loot.LootTables;
import org.bukkit.loot.Lootable;

/**
 *
 * @author weisb
 */
public class ChestLoot implements Listener{
    
    private ToggleCommand tcmd;
     public ChestLoot (ToggleCommand tc) {
         tcmd = tc;
     }
    
    @EventHandler
    public void chestLoot (PlayerInteractEvent playerEvent){
        if (!tcmd.isEnabled()){
            return;
        }
        if (playerEvent.getHand() != EquipmentSlot.HAND){
            return;
        }
        if (playerEvent.getAction() != Action.RIGHT_CLICK_BLOCK){
            return;
        }
        if (!(playerEvent.getClickedBlock().getState() instanceof Lootable )){
            return;
        }
        
        Lootable lootBlock = (Lootable) playerEvent.getClickedBlock().getState();
        
        if (lootBlock.getLootTable() == null){
            return;
        }
        
        Random random = ThreadLocalRandom.current();
        LootTables randomLoot = LootTables.values()[random.nextInt(LootTables.values().length)];
        lootBlock.setLootTable(randomLoot.getLootTable());
        ((BlockState)lootBlock).update(); 
    }
}
