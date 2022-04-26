package me.kafein.common.runnable;

import me.kafein.common.SuperCombat;
import me.kafein.common.event.TagEventChecker;
import me.kafein.common.tag.Tag;
import me.kafein.common.tag.TagManager;

import java.util.Iterator;

public class TagDurationRunnable implements Runnable {

    private final TagManager tagManager = SuperCombat.getInstance().getTagManager();

    public void run() {

        Iterator<Tag> tagIterator = tagManager.getTagMap().values().iterator();

        while (tagIterator.hasNext()) {

            Tag tag = tagIterator.next();
            if (tag.getDuration() <= 0) {
                tagIterator.remove();
                continue;
            }

            tag.removeDuration(1);
            TagEventChecker.updateTimer(tag);

        }

    }

}
