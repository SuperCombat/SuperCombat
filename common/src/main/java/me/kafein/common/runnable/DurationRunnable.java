package me.kafein.common.runnable;

import me.kafein.common.SuperCombatTag;
import me.kafein.common.tag.Tag;
import me.kafein.common.tag.TagManager;

import java.util.Iterator;

public class DurationRunnable implements Runnable {

    private final TagManager tagManager = SuperCombatTag.getInstance().getTagManager();

    public void run() {

        Iterator<Tag> tagIterator = tagManager.getTagMap().values().iterator();

        while (tagIterator.hasNext()) {

            Tag tag = tagIterator.next();
            if (tag.getDuration() <= 0) {
                tagIterator.remove();
                continue;
            }

            tag.removeDuration(1);

        }

    }

}
