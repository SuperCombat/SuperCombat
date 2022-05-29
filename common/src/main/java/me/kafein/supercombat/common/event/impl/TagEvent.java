package me.kafein.supercombat.common.event.impl;

import lombok.Getter;
import lombok.Setter;
import me.kafein.supercombat.common.event.Event;
import me.kafein.supercombat.common.event.EventType;
import me.kafein.supercombat.common.tag.Tag;

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
