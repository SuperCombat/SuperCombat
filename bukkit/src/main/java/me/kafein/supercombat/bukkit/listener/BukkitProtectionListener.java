package me.kafein.supercombat.bukkit.listener;

import me.kafein.supercombat.common.config.key.ConfigKeys;
import me.kafein.supercombat.common.tag.handler.TagHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.List;
import java.util.UUID;

public class BukkitProtectionListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTeleport(PlayerTeleportEvent event) {
        UUID playerUUID = event.getPlayer().getUniqueId();

        if (!TagHandler.find(playerUUID).isPresent()) return;

        event.setCancelled(ConfigKeys.Settings.DISABLE_TELEPORT.getValue());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        if (!TagHandler.find(playerUUID).isPresent() || !ConfigKeys.Settings.COMMAND_WHITELIST.getValue()) return;

        List<String> allowedCommands = ConfigKeys.Settings.WHITELISTED_COMMANDS.getValue();
        if (allowedCommands.contains(event.getMessage())) return;

        boolean isAdmin = player.hasPermission(ConfigKeys.Settings.ADMIN_PERM.getValue());
        if (isAdmin) return;

        event.setCancelled(true);
    }
}
