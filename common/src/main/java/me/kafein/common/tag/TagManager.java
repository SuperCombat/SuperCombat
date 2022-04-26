package me.kafein.common.tag;

import lombok.Getter;
import me.kafein.common.event.TagEventChecker;
import me.kafein.common.tag.untag.UntagReason;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class TagManager {

    private final Map<UUID, Tag> tagMap = new ConcurrentHashMap<>();

    public Optional<Tag> getTag(UUID playerUUID) {
        return Optional.ofNullable(tagMap.get(playerUUID));
    }

    public void tagPlayer(Tag tag) {
        if (tag == null) return;
        if (tagMap.containsKey(tag.getUserUUID())) TagEventChecker.checkRetag(tagMap.get(tag.getUserUUID()), tag);
        else TagEventChecker.checkTag(tag);
        tagMap.put(tag.getUserUUID(), tag);
    }

    public void unTagPlayer(UUID playerUUID, UntagReason reason) {
        TagEventChecker.checkUntag(tagMap.get(playerUUID), reason);
        tagMap.remove(playerUUID);
    }

    public boolean isTagged(UUID playerUUID) {
        return tagMap.containsKey(playerUUID);
    }

}
