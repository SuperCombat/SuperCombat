package me.kafein.supercombattag.listener;

import me.kafein.supercombattag.SuperCombatTag;
import me.kafein.supercombattag.config.ConfigKeys;
import me.kafein.supercombattag.tag.Tag;
import me.kafein.supercombattag.tag.TagManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Optional;

public class DamageListener implements Listener {

    private final TagManager tagManager = SuperCombatTag.getInstance().getTagManager();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {

        Entity entity = e.getEntity();
        Entity damager = e.getDamager();

        if (!(entity instanceof Player) && !(damager instanceof Player)) return;
        if (!(entity instanceof Player) && !ConfigKeys.MOB_TAGGING.getKey()) return;

        if (entity instanceof Player) {
            Player player = (Player) entity;
            Optional<Tag> optionalTag = tagManager.getTag(player);
            Tag tag = optionalTag.orElseGet(() -> new Tag(player.getName(), player.getUniqueId()));
            tag.setDuration(ConfigKeys.TAG_DURATION.getKey());
            tag.setOtherUser(damager.getName(), damager.getUniqueId());
        }

        if (damager instanceof Player) {
            Player player = (Player) damager;
            Optional<Tag> optionalTag = tagManager.getTag(player);
            Tag tag = optionalTag.orElseGet(() -> new Tag(player.getName(), player.getUniqueId()));
            tag.setDuration(ConfigKeys.TAG_DURATION.getKey());
            tag.setOtherUser(damager.getName(), damager.getUniqueId());
        }

    }

}
