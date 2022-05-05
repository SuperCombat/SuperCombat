package me.kafein.bukkit.listener;

import me.kafein.bukkit.SuperCombatPlugin;
import me.kafein.common.config.ConfigKeys;
import me.kafein.common.tag.untag.UntagReason;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class BukkitPunishmentListener implements Listener {

    private final SuperCombatPlugin plugin = SuperCombatPlugin.getInstance();

    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        if (!plugin.getTagManager().isTagged(playerUUID)) return;

        plugin.getTagManager().unTagPlayer(playerUUID, UntagReason.QUIT);
        if (ConfigKeys.Settings.QUIT_PUNISHMENT.getValue()) player.setHealth(0.0D);

    }

    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onKick(PlayerKickEvent event) {

        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        if (!plugin.getTagManager().isTagged(playerUUID)) return;

        plugin.getTagManager().unTagPlayer(playerUUID, UntagReason.KICK);
        if (ConfigKeys.Settings.KICK_PUNISHMENT.getValue()) player.setHealth(0.0D);

    }

}