/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.weisboys.randomsurvival;

import io.netty.util.internal.ThreadLocalRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.Lootable;

/**
 *
 * @author weisb
 */
public class BlockDrops implements Listener {
    
    Map<Material,Material> dropID = new HashMap<>();

    private ToggleCommand tcmd;
    
    public BlockDrops(ToggleCommand tc) {
       tcmd = tc;
    }
    
    @EventHandler 
    public void breakBlock (BlockBreakEvent blockBreak) {
        
        if (tcmd.isEnabled())
        {
            if ((blockBreak.getBlock().getState() instanceof Container )){
                return;
            }
            
            blockBreak.setDropItems(false);
            Block block = blockBreak.getBlock();
            Material blockType = block.getType();

            if (!dropID.containsKey(blockType))
            {

                Random random = ThreadLocalRandom.current();
                //Maps blockType to a random material
                dropID.put(blockType, Material.values()[random.nextInt(Material.values().length)]); 
            }

            Material material = dropID.get(blockType);
            ItemStack droppedItem = new ItemStack(material);
            block.getWorld().dropItemNaturally(block.getLocation(), droppedItem);
        
        }
        
      
        
    }
    
}
