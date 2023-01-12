package me.weisboys.randomsurvival;

import net.md_5.bungee.api.ChatColor;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author weisb
 */
public class BlockDrops implements Listener {

    private Map<Material,Material> dropID = new HashMap<>();

    private ToggleCommand tcmd;
    private NotifyCommand ncmd;
    private boolean st;

    public BlockDrops(ToggleCommand tc, NotifyCommand nc, boolean st) {
       tcmd = tc;
       ncmd = nc;
       this.st = st;
    }

    @EventHandler
    public void breakBlock (BlockBreakEvent blockBreak) {

        if (!tcmd.isEnabled()) return;
        if (blockBreak.getBlock().getState() instanceof Container) {
            return;
        }
        if (st && blockBreak.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
            return;
        }

        blockBreak.setDropItems(false);
        Block block = blockBreak.getBlock();
        Material blockType = block.getType();

        if (!dropID.containsKey(blockType)) {
            Random random = ThreadLocalRandom.current();
            //Maps blockType to a random material
            dropID.put(blockType, Material.values()[random.nextInt(Material.values().length)]);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (ncmd.isNotificationsEnabled(player)) {
                    player.sendMessage(ChatColor.GREEN + "New discovery: " + ChatColor.DARK_AQUA + blockType.toString() + ChatColor.GREEN + " -> " + ChatColor.AQUA + dropID.get(blockType).toString());
                }
            }
        }

        Material material = dropID.get(blockType);
        ItemStack droppedItem = new ItemStack(material);
        block.getWorld().dropItemNaturally(block.getLocation(), droppedItem);
    }

    public void resetMap() {
        dropID.clear();
    }

    public Map<Material,Material> getDropID() {
        return dropID;
    }
}
