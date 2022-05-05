package me.kafein.bukkit.listener;

import me.kafein.common.config.ConfigKeys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class BukkitProtectionListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTeleport(PlayerTeleportEvent event) {
        event.setCancelled(ConfigKeys.Settings.DISABLE_TELEPORT.getValue());
    }
}
