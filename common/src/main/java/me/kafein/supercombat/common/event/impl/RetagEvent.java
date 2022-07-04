package me.kafein.supercombat.common.event.impl;

import lombok.Getter;
import lombok.Setter;
import me.kafein.supercombat.common.event.Event;
import me.kafein.supercombat.common.event.EventType;
import me.kafein.supercombat.common.tag.Tag;

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
        return EventType.RETAG;
    }

}
