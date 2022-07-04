package me.kafein.supercombat.bukkit.listener.protection;

import me.kafein.supercombat.bukkit.tag.BukkitTagController;
import me.kafein.supercombat.bukkit.util.language.LanguageUtil;
import me.kafein.supercombat.common.blocker.Blocker;
import me.kafein.supercombat.common.blocker.Blockers;
import me.kafein.supercombat.common.tag.handler.TagHandler;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;

import java.util.UUID;

public class BukkitGlideListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onGlide(EntityToggleGlideEvent event) {
        Entity entity = event.getEntity();
        if (!BukkitTagController.isPlayer(entity)) return;

        Player player = (Player) entity;
        UUID playerUUID = player.getUniqueId();
        if (!TagHandler.find(playerUUID).isPresent()) return;

        Blocker blocker = Blockers.ELYTRA_BLOCKER.getBlocker();
        if (blocker.isEnabled()) {
            event.setCancelled(true);
            player.sendMessage(LanguageUtil.replace(blocker.getNotificationMessage()));
        }

    }

}
