package me.kafein.supercombat.bukkit.listener;

import me.kafein.supercombat.bukkit.tag.BukkitTagController;
import me.kafein.supercombat.common.config.key.ConfigKeys;
import me.kafein.supercombat.common.tag.Tag;
import me.kafein.supercombat.common.tag.handler.TagHandler;
import me.kafein.supercombat.common.tag.untag.UntagReason;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Optional;
import java.util.UUID;

public class BukkitDeathListener implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onDeath(PlayerDeathEvent event) {

        Player player = event.getEntity();
        if (player.hasMetadata("NPC")) return;
        UUID uuid = player.getUniqueId();

        Optional<Tag> optionalTag = TagHandler.find(uuid);
        if (!optionalTag.isPresent()) return;
        Tag tag = optionalTag.get();

        if (ConfigKeys.Settings.DEATH_UNTAGGING_ENEMY.getValue()) TagHandler.unTagPlayer(tag.getOtherUser().getUuid(), UntagReason.ENEMY_DEATH);
        if (ConfigKeys.Settings.DEATH_UNTAGGING_SELF.getValue()) TagHandler.unTagPlayer(uuid, UntagReason.SELF_DEATH);

    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onDeath(EntityDeathEvent event) {

        Player attacker = event.getEntity().getKiller();
        if (attacker == null) return;
        if (!BukkitTagController.isPlayer(attacker)) return;

        if (ConfigKeys.Settings.DEATH_UNTAGGING_ENEMY.getValue()) TagHandler.unTagPlayer(attacker.getUniqueId(), UntagReason.ENEMY_DEATH);

    }

}
