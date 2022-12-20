package me.weisboys.randomsurvival;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author weisb
 */
public class ResetTimer extends BukkitRunnable{

    private long gameTime;
    private World world;
    private RandomSurvival rs;
    
    public ResetTimer(RandomSurvival rs) {
        world = Bukkit.getWorlds().get(0);
        this.rs = rs;
    }
    
    @Override
    public void run() {
        if (world.getTime() < gameTime) {
            rs.getBlockDrops().resetMap();
            rs.getMobDrops().resetMap();
            Bukkit.broadcastMessage(ChatColor.GREEN + "Drops are now reset!");
        }
        
        gameTime = world.getTime();
    }

   
    
}
