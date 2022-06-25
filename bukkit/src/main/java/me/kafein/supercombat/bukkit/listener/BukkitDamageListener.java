package me.kafein.supercombat.bukkit.listener;

import me.kafein.supercombat.bukkit.tag.BukkitTagController;
import me.kafein.supercombat.common.tag.Tag;
import me.kafein.supercombat.common.tag.TagReason;
import me.kafein.supercombat.common.tag.handler.TagHandler;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BukkitDamageListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageByEntityEvent e) {
        Entity defenderEntity = e.getEntity();
        Entity attackerEntity = e.getDamager();

        if (attackerEntity instanceof Projectile) {
            Projectile projectile = (Projectile) attackerEntity;
            attackerEntity = (Entity) projectile.getShooter();
            if (attackerEntity == null) return;
        }

        if (attackerEntity.equals(defenderEntity)) return;

        if (attackerEntity instanceof Player) {
            Player attacker = (Player) attackerEntity;
            Tag attackersTag = BukkitTagController.controlPlayer(attacker, defenderEntity, TagReason.ATTACKER);
            if (attackersTag != null) TagHandler.tagPlayer(attackersTag);
        }

        if (defenderEntity instanceof Player) {
            Player defender = (Player) defenderEntity;
            TagHandler.tagPlayer(BukkitTagController.controlPlayer(defender, attackerEntity, TagReason.DEFENDER));
        }

    }

}
