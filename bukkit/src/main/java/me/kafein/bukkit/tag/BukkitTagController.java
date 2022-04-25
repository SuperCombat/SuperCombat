package me.kafein.bukkit.tag;

import me.kafein.common.config.ConfigKeys;
import me.kafein.common.tag.Tag;
import me.kafein.common.tag.TagCause;
import me.kafein.common.tag.TagReason;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.Nullable;

public class BukkitTagController {

    @Nullable
    public static Tag controlPlayer(Player player, Entity enemy, TagReason tagReason) {

        if ((!isPlayer(enemy) && !ConfigKeys.MOB_TAGGING.getValue()) || isNPC(enemy)) {
            return null;
        }

        Tag tag = new Tag(player.getName(), player.getUniqueId());
        tag.setDuration(ConfigKeys.TAG_DURATION.getValue());
        tag.setTagReason(tagReason);
        tag.setTagCause(isPlayer(enemy) ? TagCause.PLAYER : TagCause.MOB);
        tag.setOtherUser(enemy.getName(), enemy.getUniqueId());

        return tag;

    }

    public static boolean isPlayer(Entity entity) {
        return entity instanceof Player;
    }

    public static boolean isNPC(Entity entity) {
        return entity.hasMetadata("NPC");
    }

}
