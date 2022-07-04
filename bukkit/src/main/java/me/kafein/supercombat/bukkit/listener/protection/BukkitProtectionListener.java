package me.kafein.supercombat.bukkit.listener.protection;

import me.kafein.supercombat.bukkit.util.language.LanguageUtil;
import me.kafein.supercombat.common.blocker.Blocker;
import me.kafein.supercombat.common.blocker.Blockers;
import me.kafein.supercombat.common.config.key.ConfigKeys;
import me.kafein.supercombat.common.tag.handler.TagHandler;
import me.kafein.supercombat.common.tag.untag.UntagReason;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class BukkitProtectionListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        if (!TagHandler.find(playerUUID).isPresent()) return;

        Blocker blocker = Blockers.TELEPORT_BLOCKER.getBlocker();
        event.setCancelled(blocker.isEnabled());

        if (event.getCause() == PlayerTeleportEvent.TeleportCause.PLUGIN
                && ConfigKeys.Settings.PLUGIN_TELEPORT_ENABLED.getValue()) {
            event.setCancelled(false);
        }

        if (event.isCancelled()) {
            player.sendMessage(LanguageUtil.replace(blocker.getNotificationMessage()));
        } else if (ConfigKeys.Settings.TELEPORT_UNTAGGING.getValue()) {
            TagHandler.unTagPlayer(playerUUID, UntagReason.TELEPORT);
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        if (!TagHandler.find(playerUUID).isPresent()) return;

        String command = event.getMessage().substring(1);
        Blocker commandBlocker = Blockers.COMMAND_BLOCKER.getBlocker();
        if (!commandBlocker.isEnabled() || commandBlocker.control(command)) return;

        boolean isAdmin = player.hasPermission(ConfigKeys.Settings.ADMIN_PERM.getValue());
        if (isAdmin) return;

        event.setCancelled(true);
        player.sendMessage(LanguageUtil.replace(commandBlocker.getNotificationMessage(),
                new String[]{"%command%"},
                new String[]{command}
        ));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onToggleFly(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        if (!TagHandler.find(playerUUID).isPresent()) return;

        Blocker blocker = Blockers.FLY_BLOCKER.getBlocker();
        if (blocker.isEnabled()) {
            event.setCancelled(true);
            player.sendMessage(LanguageUtil.replace(blocker.getNotificationMessage()));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        if (!TagHandler.find(playerUUID).isPresent()) return;

        ItemStack item = event.getItem();
        Material material = item.getType();
        if (!material.name().equals("CHORUS_FRUIT")) return;

        Blocker blocker = Blockers.CHORUSFRUIT_BLOCKER.getBlocker();
        if (blocker.isEnabled()) {
            event.setCancelled(true);
            player.sendMessage(LanguageUtil.replace(blocker.getNotificationMessage()));
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onOpenInventory(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        if (!TagHandler.find(playerUUID).isPresent()) return;

        InventoryType type = event.getInventory().getType();
        if (type == InventoryType.CRAFTING) return;

        Blocker blocker = Blockers.INVENTORY_BLOCKER.getBlocker();
        if (blocker.isEnabled()) {
            event.setCancelled(true);
            player.sendMessage(LanguageUtil.replace(blocker.getNotificationMessage()));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        if (!TagHandler.find(playerUUID).isPresent()) return;

        Blocker blocker = Blockers.BLOCK_BREAK_BLOCKER.getBlocker();
        if (blocker.isEnabled()) {
            event.setCancelled(true);
            player.sendMessage(LanguageUtil.replace(blocker.getNotificationMessage()));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        if (!TagHandler.find(playerUUID).isPresent()) return;

        Blocker blocker = Blockers.BLOCK_PLACE_BLOCKER.getBlocker();
        if (blocker.isEnabled()) {
            event.setCancelled(true);
            player.sendMessage(LanguageUtil.replace(blocker.getNotificationMessage()));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        if (!TagHandler.find(playerUUID).isPresent()) return;

        Blocker blocker = Blockers.ITEM_DROP_BLOCKER.getBlocker();
        if (blocker.isEnabled()) {
            event.setCancelled(true);
            player.sendMessage(LanguageUtil.replace(blocker.getNotificationMessage()));
        }
    }

}
