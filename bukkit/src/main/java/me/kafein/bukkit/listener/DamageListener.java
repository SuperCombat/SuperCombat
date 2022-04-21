package me.kafein.bukkit.listener;

import me.kafein.common.SuperCombatTag;
import me.kafein.common.config.ConfigKeys;
import me.kafein.common.tag.Tag;
import me.kafein.common.tag.TagManager;
import me.kafein.common.tag.TagReason;
import me.kafein.common.tag.TagType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Optional;

public class DamageListener implements Listener {

    private final TagManager tagManager = SuperCombatTag.getInstance().getTagManager();

    @EventHandler (priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent e) {

        Entity entity = e.getEntity();
        Entity damager = e.getDamager();

        if (!(entity instanceof Player) && (!(damager instanceof Player) || !ConfigKeys.MOB_TAGGING.getValue())) return;

        tagManager.getTagMap().values().forEach(tag -> Bukkit.broadcastMessage(tag.getUserName() + " : " + tag.getDuration()));

        if (entity instanceof Player) {
            Player player = (Player) entity;
            Optional<Tag> optionalTag = tagManager.getTag(player.getUniqueId());
            Tag tag = optionalTag.orElseGet(() -> new Tag(player.getName(), player.getUniqueId()));
            tag.setDuration(ConfigKeys.TAG_DURATION.getValue());
            tag.setOtherUser(damager.getName(), damager.getUniqueId());
            tag.setTagType(TagType.PLAYER);
            tag.setTagReason(TagReason.DEFENDER);
            tagManager.addTag(tag);
        }

        if (damager instanceof Player) {
            Player player = (Player) damager;
            Optional<Tag> optionalTag = tagManager.getTag(player.getUniqueId());
            Tag tag = optionalTag.orElseGet(() -> new Tag(player.getName(), player.getUniqueId()));
            tag.setDuration(ConfigKeys.TAG_DURATION.getValue());
            tag.setOtherUser(damager.getName(), damager.getUniqueId());
            tag.setTagType((entity instanceof Player) ? TagType.PLAYER : TagType.MOB);
            tag.setTagReason(TagReason.ATTACKER);
            tagManager.addTag(tag);
        }

    }

}
