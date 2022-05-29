package me.kafein.supercombat.common.event.impl;

import lombok.Getter;
import lombok.Setter;
import me.kafein.supercombat.common.event.Event;
import me.kafein.supercombat.common.event.EventType;
import me.kafein.supercombat.common.tag.Tag;
import me.kafein.supercombat.common.tag.untag.UntagReason;

public class UntagEvent implements Event {

    @Getter private final Tag tag;
    @Getter private final UntagReason reason;
    @Getter @Setter
    private boolean cancelled;

    public UntagEvent(Tag tag, UntagReason reason, boolean cancelled) {
        this.tag = tag;
        this.reason = reason;
        this.cancelled = cancelled;
    }

    public EventType getType() {
        return EventType.UN_TAG;
    }

}
