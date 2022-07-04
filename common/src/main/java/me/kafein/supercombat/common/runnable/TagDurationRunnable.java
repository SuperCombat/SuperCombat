package me.kafein.supercombat.common.runnable;

import me.kafein.supercombat.common.listener.ListenerChecker;
import me.kafein.supercombat.common.tag.handler.TagHandler;
import me.kafein.supercombat.common.tag.untag.UntagReason;

public class TagDurationRunnable implements Runnable {

    public void run() {

        TagHandler.findAll().removeIf(tag -> {
            if (tag.getDuration() <= 0 || ListenerChecker.updateTag(tag)) {
                return !ListenerChecker.checkUntag(tag, UntagReason.EXPIRE);
            }
            return false;
        });

    }

}
