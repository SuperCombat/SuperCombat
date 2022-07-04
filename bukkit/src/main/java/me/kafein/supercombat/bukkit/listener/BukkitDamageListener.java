package me.kafein.supercombat.bukkit.listener;

import me.kafein.supercombat.bukkit.tag.BukkitTagController;
import me.kafein.supercombat.common.blocker.Blocker;
import me.kafein.supercombat.common.blocker.Blockers;
import me.kafein.supercombat.common.config.key.ConfigKeys;
import me.kafein.supercombat.common.tag.Tag;
import me.kafein.supercombat.common.tag.TagReason;
import me.kafein.supercombat.common.tag.handler.TagHandler;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

public class BukkitDamageListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity defenderEntity = event.getEntity();
        Entity attackerEntity = event.getDamager();

        Blocker worldBlocker = Blockers.WORLD_BLOCKER.getBlocker();
        if (worldBlocker.isEnabled() && !worldBlocker.control(attackerEntity.getWorld().getName())) return;

        if (ConfigKeys.Settings.BYPASS_TAGGING_ENABLED.getValue()) {
            String permission = ConfigKeys.Settings.ADMIN_PERM.getValue();
            if (defenderEntity.hasPermission(permission) || attackerEntity.hasPermission(permission)) return;
        }

        if (attackerEntity instanceof Projectile) {
            if (!ConfigKeys.Settings.PROJECTILE_TAGGING.getValue()) return;
            Projectile projectile = (Projectile) attackerEntity;
            ProjectileSource source = projectile.getShooter();
            if (!(source instanceof Entity)) return;
            attackerEntity = (Entity) source;
        }

        if (attackerEntity.equals(defenderEntity)) return;

        Tag attackerTag = BukkitTagController.controlPlayer(attackerEntity, defenderEntity, TagReason.ATTACKER);
        TagHandler.tagPlayer(attackerTag);

        if (!ConfigKeys.Settings.ONLY_TAGGING_SELF.getValue()) {
            Tag defenderTag = BukkitTagController.controlPlayer(defenderEntity, attackerEntity, TagReason.DEFENDER);
            TagHandler.tagPlayer(defenderTag);
        }

    }

}
