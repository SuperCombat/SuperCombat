package me.kafein.common.event;

import me.kafein.common.tag.Tag;
import me.kafein.common.tag.untag.UntagReason;

public interface TagEvent {

    void onTag(Tag tag);

    void onRetag(Tag tag, Tag newTag);

    void onUntag(Tag tag, UntagReason reason);

    void onUpdateTimer(Tag tag);

}
