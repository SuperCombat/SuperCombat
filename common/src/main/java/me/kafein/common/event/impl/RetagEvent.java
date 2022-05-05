package me.kafein.common.event.impl;

import lombok.Getter;
import lombok.Setter;
import me.kafein.common.event.Event;
import me.kafein.common.event.EventType;
import me.kafein.common.tag.Tag;

public class RetagEvent implements Event {

    @Getter private final Tag oldTag;
    @Getter private final Tag newTag;
    @Getter @Setter
    private boolean cancelled;

    public RetagEvent(Tag oldTag, Tag newTag, boolean cancelled) {
        this.oldTag = oldTag;
        this.newTag = newTag;
        this.cancelled = cancelled;
    }

    public EventType getType() {
        return EventType.RE_TAG;
    }

}
