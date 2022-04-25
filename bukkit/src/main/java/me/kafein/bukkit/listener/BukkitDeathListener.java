package me.kafein.bukkit.listener;

import me.kafein.bukkit.tag.BukkitTagController;
import me.kafein.common.SuperCombat;
import me.kafein.common.tag.TagManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class BukkitDeathListener implements Listener {

    private final TagManager tagManager = SuperCombat.getInstance().getTagManager();

    @EventHandler (priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onDeath(EntityDeathEvent event) {

        Entity entity = event.getEntity();
        if (!BukkitTagController.isPlayer(entity) || BukkitTagController.isNPC(entity)) return;

        Player player = (Player) entity;
        tagManager.unTagPlayer(player.getUniqueId());

    }

}
