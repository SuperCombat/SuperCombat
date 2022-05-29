package me.kafein.supercombat.common.tag.handler;

import lombok.Getter;
import me.kafein.supercombat.common.listener.ListenerChecker;
import me.kafein.supercombat.common.tag.Tag;
import me.kafein.supercombat.common.tag.untag.UntagReason;
import me.kafein.supercombat.common.tag.user.TagUser;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TagHandler {

    @Getter private static final Map<UUID, Tag> tagMap = new ConcurrentHashMap<>();

    public static Optional<Tag> find(UUID playerUUID) {
        return Optional.ofNullable(tagMap.get(playerUUID));
    }

    public static void tagPlayer(Tag tag) {
        if (tag == null) return;
        TagUser user = tag.getUser();
        boolean isCancelled = (tagMap.containsKey(user.getUuid())
                ? ListenerChecker.checkRetag(tagMap.get(user.getUuid()), tag)
                : ListenerChecker.checkTag(tag));
        if (isCancelled) return;
        tagMap.put(user.getUuid(), tag);
    }

    public static void unTagPlayer(UUID playerUUID, UntagReason reason) {
        if (!tagMap.containsKey(playerUUID)) return;
        if (ListenerChecker.checkUntag(tagMap.get(playerUUID), reason)) return;
        tagMap.remove(playerUUID);
    }

}
