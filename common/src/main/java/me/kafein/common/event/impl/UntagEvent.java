package me.kafein.common.event.impl;

import lombok.Getter;
import lombok.Setter;
import me.kafein.common.event.Event;
import me.kafein.common.event.EventType;
import me.kafein.common.tag.Tag;
import me.kafein.common.tag.untag.UntagReason;

public class UntagEvent implements Event {

    @Getter final private Tag tag;
    @Getter final private UntagReason reason;
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
