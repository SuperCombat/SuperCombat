package me.kafein.common.event;

import java.util.HashSet;
import java.util.Set;

public class TagEventRegisteror {

    final private static Set<TagEvent> tagEvents = new HashSet<>();

    public static void register(TagEvent tagEvent) {
        tagEvents.add(tagEvent);
    }

    public static void unregister(TagEvent tagEvent) {
        tagEvents.remove(tagEvent);
    }

    public static Set<TagEvent> getTagEvents() {
        return tagEvents;
    }

}
