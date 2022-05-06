package me.kafein.bukkit.listener;

import me.kafein.bukkit.SuperCombatPlugin;
import me.kafein.common.config.ConfigKeys;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.List;
import java.util.UUID;

public class BukkitProtectionListener implements Listener {

    private final SuperCombatPlugin plugin = SuperCombatPlugin.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTeleport(PlayerTeleportEvent event) {
        UUID playerUUID = event.getPlayer().getUniqueId();

        if (!plugin.getTagManager().isTagged(playerUUID)) return;

        event.setCancelled(ConfigKeys.Settings.DISABLE_TELEPORT.getValue());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        if (!plugin.getTagManager().isTagged(playerUUID) || !ConfigKeys.Settings.COMMAND_WHITELIST.getValue()) return;

        List<String> allowedCommands = ConfigKeys.Settings.WHITELISTED_COMMANDS.getValue();
        if (allowedCommands.contains(event.getMessage())) return;

        boolean isAdmin = player.hasPermission(ConfigKeys.Settings.ADMIN_PERM.getValue());
        event.setCancelled(!isAdmin);
    }
}
