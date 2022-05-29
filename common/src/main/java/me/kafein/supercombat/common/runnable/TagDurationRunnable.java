package me.kafein.supercombat.common.runnable;

import me.kafein.supercombat.common.listener.ListenerChecker;
import me.kafein.supercombat.common.tag.Tag;
import me.kafein.supercombat.common.tag.handler.TagHandler;
import me.kafein.supercombat.common.tag.untag.UntagReason;

import java.util.Iterator;

public class TagDurationRunnable implements Runnable {

    public void run() {

        Iterator<Tag> tagIterator = TagHandler.getTagMap().values().iterator();

        while (tagIterator.hasNext()) {
            Tag tag = tagIterator.next();
            if (tag.getDuration() > 0 && !ListenerChecker.updateTag(tag)) continue;
            boolean isCancelled = ListenerChecker.checkUntag(tag, UntagReason.EXPIRE);
            if (!isCancelled) tagIterator.remove();
        }

    }

}
