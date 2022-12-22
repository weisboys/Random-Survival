package me.weisboys.randomsurvival;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

/**
 *
 * @author weisb
 */
public class ToggleCommand implements TabExecutor{
    
    private boolean enabled = true;
    
    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmnd, String string, String[] strings) {
        List<String> empty = new ArrayList<>();
        
        return empty;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        if(enabled)
        {
            enabled = false;
            cs.sendMessage(ChatColor.GREEN + "RandomSurvival " + ChatColor.RED + "disabled");
        }
        else {
            enabled = true;
            cs.sendMessage(ChatColor.GREEN + "RandomSurvival " + ChatColor.YELLOW + "enabled");
        }
        return true;
    }
    
    public boolean isEnabled() {
        return enabled;
    } 
    
}
