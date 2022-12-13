/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.weisboys.randomsurvival;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author weisb
 */
public class RandomSurvival extends JavaPlugin implements Listener {
    @Override
    public void onEnable (){
        ToggleCommand tc = new ToggleCommand();
        
        Bukkit.getPluginManager().registerEvents(new BlockDrops(tc), this);
        Bukkit.getPluginManager().registerEvents(new MobDrops(tc), this);
        Bukkit.getPluginManager().registerEvents(new ChestLoot(tc), this);
        
        getCommand("rstoggle").setExecutor(tc);
    }
    
}