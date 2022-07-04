package me.kafein.supercombat.bukkit.listener.protection;

import lombok.Getter;
import me.kafein.supercombat.bukkit.tag.BukkitTagController;
import me.kafein.supercombat.bukkit.util.ProtocolVersion;
import me.kafein.supercombat.bukkit.util.language.LanguageUtil;
import me.kafein.supercombat.common.blocker.Blocker;
import me.kafein.supercombat.common.blocker.Blockers;
import me.kafein.supercombat.common.tag.handler.TagHandler;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.util.UUID;

public class BukkitItemPickupListener {

    @Getter private static final Class<?> selectedClass;

    static {
        if (ProtocolVersion.isNewerOrEqual(ProtocolVersion.Version.v1_12)) {
            selectedClass = NewVersionItemPickupListener.class;
        }
        else selectedClass = OldVersionItemPickupListener.class;
    }

    public static class NewVersionItemPickupListener implements Listener {

        @EventHandler(priority = EventPriority.HIGHEST)
        public void onItemPickup(EntityPickupItemEvent event) {
            Entity entity = event.getEntity();
            if (!BukkitTagController.isPlayer(entity)) return;

            Player player = (Player) entity;
            UUID playerUUID = player.getUniqueId();
            if (!TagHandler.find(playerUUID).isPresent()) return;

            Blocker blocker = Blockers.ITEM_PICKUP_BLOCKER.getBlocker();
            if (blocker.isEnabled()) {
                event.setCancelled(true);
                player.sendMessage(LanguageUtil.replace(blocker.getNotificationMessage()));
            }
        }

    }

    public static class OldVersionItemPickupListener implements Listener {

        @EventHandler(priority = EventPriority.HIGHEST)
        public void onItemPickup(PlayerPickupItemEvent event) {
            Player player = event.getPlayer();
            UUID playerUUID = player.getUniqueId();
            if (!TagHandler.find(playerUUID).isPresent()) return;

            Blocker blocker = Blockers.ITEM_PICKUP_BLOCKER.getBlocker();
            if (blocker.isEnabled()) {
                event.setCancelled(true);
                player.sendMessage(LanguageUtil.replace(blocker.getNotificationMessage()));
            }
        }

    }

}
