package me.kafein.bukkit.listener;

import me.kafein.bukkit.tag.BukkitTagController;
import me.kafein.common.SuperCombat;
import me.kafein.common.tag.TagManager;
import me.kafein.common.tag.TagReason;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BukkitDamageListener implements Listener {

    private final TagManager tagManager = SuperCombat.getInstance().getTagManager();

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
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

}
