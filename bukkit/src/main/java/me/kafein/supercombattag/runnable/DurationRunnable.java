package me.kafein.supercombattag.runnable;

import me.kafein.supercombattag.SuperCombatTag;
import me.kafein.supercombattag.tag.Tag;
import me.kafein.supercombattag.tag.TagManager;

import java.util.Iterator;

public class DurationRunnable implements Runnable {

    private final TagManager tagManager = SuperCombatTag.getInstance().getTagManager();

    @Override
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
