package me.kafein.common.runnable;

import me.kafein.common.tag.Tag;

import java.util.Iterator;

public class DurationRunnable implements Runnable {


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
