package me.kafein.bukkit.listener;

import me.kafein.bukkit.tag.BukkitTagController;
import me.kafein.common.SuperCombat;
import me.kafein.common.tag.TagManager;
import me.kafein.common.tag.TagReason;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

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

        plugin.getTagManager().getTagMap().forEach((player, tag) -> {
            Bukkit.broadcastMessage(tag.getUserName() + " : " + tag.getDuration());
        });

        Player attacker = (Player) attackerEntity;
        plugin.getTagManager().tagPlayer(BukkitTagController.controlPlayer(attacker, defenderEntity, TagReason.ATTACKER));

        if (defenderEntity instanceof Player) {
            Player defender = (Player) defenderEntity;
            plugin.getTagManager().tagPlayer(BukkitTagController.controlPlayer(defender, attacker, TagReason.DEFENDER));
        }

    }

    /*@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onHit(ProjectileHitEvent event) {

        Entity defenderEntity =
        Bukkit.broadcastMessage(defenderEntity.getType().name());
        Entity attackerEntity = (Entity) event.getEntity().getShooter();

        if (!(defenderEntity instanceof LivingEntity)) return;
        if (!BukkitTagController.isPlayer(attackerEntity) || BukkitTagController.isNPC(attackerEntity)) return;

        plugin.getTagManager(.getTagMap().forEach((player, tag) -> {
            Bukkit.broadcastMessage(tag.getUserName() + " : " + tag.getDuration());
        });

        Player attacker = (Player) attackerEntity;
        plugin.getTagManager(.tagPlayer(BukkitTagController.controlPlayer(attacker, defenderEntity, TagReason.ATTACKER));

        if (defenderEntity instanceof Player) {
            Player defender = (Player) defenderEntity;
            plugin.getTagManager(.tagPlayer(BukkitTagController.controlPlayer(defender, attacker, TagReason.DEFENDER));
        }

    }*/

}
