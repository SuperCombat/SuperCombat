package me.kafein.supercombat.bukkit.listener;

import me.kafein.supercombat.common.config.key.ConfigKeys;
import me.kafein.supercombat.common.tag.handler.TagHandler;
import me.kafein.supercombat.common.tag.untag.UntagReason;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class BukkitPunishmentListener implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        if (!TagHandler.find(playerUUID).isPresent()) return;

        TagHandler.unTagPlayer(playerUUID, UntagReason.QUIT);
        if (ConfigKeys.Settings.QUIT_PUNISHMENT.getValue()) player.setHealth(0.0D);

        executePunishmentCommands(player);
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onKick(PlayerKickEvent event) {

        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        if (!TagHandler.find(playerUUID).isPresent()) return;

        TagHandler.unTagPlayer(playerUUID, UntagReason.KICK);
        if (ConfigKeys.Settings.KICK_PUNISHMENT.getValue()) player.setHealth(0.0D);

        executePunishmentCommands(player);
    }

    private void executePunishmentCommands(Player player) {
        ConfigKeys.Settings.PUNISHMENT_COMMANDS.getValue().forEach(command -> {
            command = command.replace("%player%", player.getName());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        });
    }

}
