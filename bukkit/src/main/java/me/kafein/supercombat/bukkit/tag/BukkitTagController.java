package me.kafein.supercombat.bukkit.tag;

import me.kafein.supercombat.common.config.key.ConfigKeys;
import me.kafein.supercombat.common.tag.Tag;
import me.kafein.supercombat.common.tag.TagCause;
import me.kafein.supercombat.common.tag.TagReason;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class BukkitTagController {

    @Nullable
    public static Tag controlPlayer(Player player, Entity enemy, TagReason tagReason) {
        if ((!isPlayer(enemy) && !ConfigKeys.Settings.MOB_TAGGING.getValue())) return null;
        if (enemy instanceof Animals) return null;

        return Tag.of(
                player.getName(), player.getUniqueId(),
                enemy.getName(), enemy.getUniqueId(),
                isPlayer(enemy) ? TagCause.PLAYER : TagCause.MOB,
                tagReason,
                ConfigKeys.Settings.TAG_DURATION.getValue());
    }

    public static boolean isPlayer(Entity entity) {
        return entity instanceof Player;
    }

}
