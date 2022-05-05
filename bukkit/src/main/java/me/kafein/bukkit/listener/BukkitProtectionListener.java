package me.kafein.bukkit.listener;

import me.kafein.bukkit.SuperCombatPlugin;
import me.kafein.common.config.ConfigKeys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.UUID;

public class BukkitProtectionListener implements Listener {

    private final SuperCombatPlugin plugin = SuperCombatPlugin.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTeleport(PlayerTeleportEvent event) {
        UUID playerUUID = event.getPlayer().getUniqueId();

        if (!plugin.getTagManager().isTagged(playerUUID)) return;
        event.setCancelled(ConfigKeys.Settings.DISABLE_TELEPORT.getValue());
    }
}
