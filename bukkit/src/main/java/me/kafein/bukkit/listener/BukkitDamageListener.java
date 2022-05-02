package me.kafein.bukkit.listener;

import me.kafein.bukkit.tag.BukkitTagController;
import me.kafein.common.SuperCombat;
import me.kafein.common.tag.TagReason;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BukkitDamageListener implements Listener {

    private final SuperCombat plugin = SuperCombat.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent e) {

        Entity defenderEntity = e.getEntity();
        Entity attackerEntity = e.getDamager();

        if (attackerEntity instanceof Projectile) {
            Projectile projectile = (Projectile) attackerEntity;
            attackerEntity = (Entity) projectile.getShooter();
            if (attackerEntity == null) return;
        }

        if (attackerEntity.equals(defenderEntity)) return;

        if (!BukkitTagController.isPlayer(attackerEntity)) return;

        Player attacker = (Player) attackerEntity;
        plugin.getTagManager().tagPlayer(BukkitTagController.controlPlayer(attacker, defenderEntity, TagReason.ATTACKER));

        if (defenderEntity instanceof Player) {
            Player defender = (Player) defenderEntity;
            plugin.getTagManager().tagPlayer(BukkitTagController.controlPlayer(defender, attacker, TagReason.DEFENDER));
        }

    }

}
