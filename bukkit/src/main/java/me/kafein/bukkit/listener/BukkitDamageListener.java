package me.kafein.bukkit.listener;

import me.kafein.bukkit.tag.BukkitTagController;
import me.kafein.common.SuperCombat;
import me.kafein.common.tag.TagManager;
import me.kafein.common.tag.TagReason;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class BukkitDamageListener implements Listener {

    private final TagManager tagManager = SuperCombat.getInstance().getTagManager();

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent e) {

        Entity defenderEntity = e.getEntity();
        Entity attackerEntity = e.getDamager();

        if (!BukkitTagController.isPlayer(attackerEntity) || BukkitTagController.isNPC(attackerEntity)) return;

        tagManager.getTagMap().forEach((player, tag) -> {
            Bukkit.broadcastMessage(tag.getUserName() + " : " + tag.getDuration());
        });

        Player attacker = (Player) attackerEntity;
        tagManager.tagPlayer(BukkitTagController.controlPlayer(attacker, defenderEntity, TagReason.ATTACKER));

        if (defenderEntity instanceof Player) {
            Player defender = (Player) defenderEntity;
            tagManager.tagPlayer(BukkitTagController.controlPlayer(defender, attacker, TagReason.DEFENDER));
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onHit(ProjectileHitEvent event) {

        Projectile projectile = event.getEntity();
        if (!(projectile.getShooter() instanceof Entity)) return;

        Entity defenderEntity = event.getHitEntity();
        Entity attackerEntity = (Entity) projectile.getShooter();

        if (!BukkitTagController.isPlayer(attackerEntity) || BukkitTagController.isNPC(attackerEntity)) return;

        tagManager.getTagMap().forEach((player, tag) -> {
            Bukkit.broadcastMessage(tag.getUserName() + " : " + tag.getDuration());
        });

        Player attacker = (Player) attackerEntity;
        tagManager.tagPlayer(BukkitTagController.controlPlayer(attacker, defenderEntity, TagReason.ATTACKER));

        if (defenderEntity instanceof Player) {
            Player defender = (Player) defenderEntity;
            tagManager.tagPlayer(BukkitTagController.controlPlayer(defender, attacker, TagReason.DEFENDER));
        }


    }

}
