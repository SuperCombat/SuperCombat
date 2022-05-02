package me.kafein.common.listener;

import me.kafein.common.event.impl.RetagEvent;
import me.kafein.common.event.impl.TagEvent;
import me.kafein.common.event.impl.UntagEvent;
import me.kafein.common.event.impl.UpdateTagEvent;
import me.kafein.common.tag.Tag;
import me.kafein.common.tag.untag.UntagReason;

public class ListenerChecker {

    public static boolean updateTag(Tag tag) {
        UpdateTagEvent event = new UpdateTagEvent(tag, false);
        for (Listener listener : ListenerRegisteror.getListeners()) {
            listener.onUpdateTag(event);
            if (event.isCancelled()) return false;
        }
        return true;
    }

    public static boolean checkTag(Tag tag) {
        TagEvent event = new TagEvent(tag, false);
        for (Listener listener : ListenerRegisteror.getListeners()) {
            listener.onTag(event);
            if (event.isCancelled()) return false;
        }
        return true;
    }

    public static boolean checkRetag(Tag oldTag, Tag newTag) {
        RetagEvent event = new RetagEvent(oldTag, newTag, false);
        for (Listener listener : ListenerRegisteror.getListeners()) {
            listener.onRetag(event);
            if (event.isCancelled()) return false;
        }
        return true;
    }

    public static boolean checkUntag(Tag tag, UntagReason reason) {
        UntagEvent event = new UntagEvent(tag, reason, false);
        for (Listener listener : ListenerRegisteror.getListeners()) {
            listener.onUntag(event);
            if (event.isCancelled()) return false;
        }
        return true;
    }

}
