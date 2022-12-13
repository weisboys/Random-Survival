package me.weisboys.randomsurvival;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author weisb
 */
public class RandomSurvival extends JavaPlugin implements Listener {
    private BlockDrops bd;
    private MobDrops md;
    
    @Override
    public void onEnable (){
        ToggleCommand tc = new ToggleCommand();
        bd = new BlockDrops(tc);
        md = new MobDrops(tc);
        ResetTimer rt = new ResetTimer(this);
        
        Bukkit.getPluginManager().registerEvents(bd, this);
        Bukkit.getPluginManager().registerEvents(md, this);
        Bukkit.getPluginManager().registerEvents(new ChestLoot(tc), this);
        rt.runTaskTimer(this, 0, 100);
        
        getCommand("rstoggle").setExecutor(tc);
    }

    public BlockDrops getBlockDrops() {
        return bd;
    }

    public MobDrops getMobDrops() {
        return md;
    }
    
    
}