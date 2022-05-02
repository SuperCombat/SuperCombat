package me.kafein.common.listener;

import me.kafein.common.event.impl.RetagEvent;
import me.kafein.common.event.impl.TagEvent;
import me.kafein.common.event.impl.UntagEvent;
import me.kafein.common.event.impl.UpdateTagEvent;

public abstract class Listener {

    public void onTag(TagEvent event) {}

    public void onRetag(RetagEvent event) {}

    public void onUntag(UntagEvent event) {}

    public void onUpdateTag(UpdateTagEvent event) {}

}
