package me.kafein.common.runnable;

import me.kafein.common.SuperCombat;
import me.kafein.common.listener.ListenerChecker;
import me.kafein.common.tag.Tag;
import me.kafein.common.tag.TagManager;
import me.kafein.common.tag.untag.UntagReason;

import java.util.Iterator;

public class TagDurationRunnable implements Runnable {

    private final SuperCombat plugin = SuperCombat.getInstance();

    public void run() {

        Iterator<Tag> tagIterator = plugin.getTagManager().getTagMap().values().iterator();

        while (tagIterator.hasNext()) {

            Tag tag = tagIterator.next();
            if (tag.getDuration() <= 0 || ListenerChecker.updateTag(tag)) {
                boolean isCancelled = ListenerChecker.checkUntag(tag, UntagReason.EXPIRE);
                if (!isCancelled) {
                    tagIterator.remove();
                    continue;
                }
            }

            tag.removeDuration(1);

        }

    }

}
