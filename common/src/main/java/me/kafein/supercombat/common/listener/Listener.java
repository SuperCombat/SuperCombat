package me.kafein.supercombat.common.listener;

import me.kafein.supercombat.common.event.impl.RetagEvent;
import me.kafein.supercombat.common.event.impl.TagEvent;
import me.kafein.supercombat.common.event.impl.UntagEvent;
import me.kafein.supercombat.common.event.impl.UpdateTagEvent;

public abstract class Listener {

    public void onTag(TagEvent event) {}

    public void onRetag(RetagEvent event) {}

    public void onUntag(UntagEvent event) {}

    public void onUpdateTag(UpdateTagEvent event) {}

}
