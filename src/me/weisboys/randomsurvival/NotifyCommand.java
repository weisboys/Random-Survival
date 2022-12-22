package me.weisboys.randomsurvival;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import net.md_5.bungee.api.ChatColor;

public class NotifyCommand implements TabExecutor {
    private Set<UUID> players = new HashSet<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return new ArrayList<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (players.remove(player.getUniqueId())) {
            player.sendMessage(ChatColor.GREEN + "Random discovery notifications " + ChatColor.RED + "disabled");
            return true;
        }
        players.add(player.getUniqueId());
        player.sendMessage(ChatColor.GREEN + "Random discovery notifications " + ChatColor.YELLOW + "enabled");
        return true;
    }

    public boolean isNotificationsEnabled(Player sender) {
        return players.contains(sender.getUniqueId());
    }
}
