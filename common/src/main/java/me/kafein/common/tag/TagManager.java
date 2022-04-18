package me.kafein.common.tag;

import lombok.Getter;

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

    public void addTag(Tag tag) {
        tagMap.put(tag.getUserUUID(), tag);
    }

    public void removeTag(UUID playerUUID) {
        tagMap.remove(playerUUID);
    }

    public boolean containsTag(UUID playerUUID) {
        return tagMap.containsKey(playerUUID);
    }

}
