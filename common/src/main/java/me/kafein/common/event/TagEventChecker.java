package me.kafein.common.event;

import me.kafein.common.tag.Tag;
import me.kafein.common.tag.untag.UntagReason;

public class TagEventChecker {

    public static void updateTimer(Tag tag) {
        TagEventRegisteror.getTagEvents().forEach(event -> {
            event.onUpdateTimer(tag);
        });
    }

    public static Tag checkTag(Tag tag) {
        TagEventRegisteror.getTagEvents().forEach(event -> {
            event.onTag(tag);
        });
        return tag;
    }

    public static Tag checkRetag(Tag tag, Tag newTag) {
        TagEventRegisteror.getTagEvents().forEach(event -> {
            event.onRetag(tag, newTag);
        });
        return newTag;
    }

    public static Tag checkUntag(Tag tag, UntagReason reason) {
        TagEventRegisteror.getTagEvents().forEach(event -> {
            event.onUntag(tag, reason);
        });
        return tag;
    }

}
