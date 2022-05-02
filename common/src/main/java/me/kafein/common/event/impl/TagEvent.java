package me.kafein.common.event.impl;

import lombok.Getter;
import lombok.Setter;
import me.kafein.common.event.Event;
import me.kafein.common.event.EventType;
import me.kafein.common.tag.Tag;

public class TagEvent implements Event {

    @Getter final private Tag tag;
    @Getter @Setter private boolean cancelled;

    public TagEvent(Tag tag, boolean cancelled) {
        this.tag = tag;
        this.cancelled = cancelled;
    }

    public EventType getType() {
        return EventType.TAG;
    }

}
