package me.kafein.supercombat.common.blocker.type.item;

import me.kafein.supercombat.common.blocker.Blocker;
import me.kafein.supercombat.common.config.key.ConfigKeys;

public class ItemDropBlocker extends Blocker {

    @Override
    public void init() {
        enabled = ConfigKeys.Settings.ITEM_DROP_BLOCKER_ENABLED.getValue();
        notificationMessage = ConfigKeys.Language.ITEM_DROP_IS_BLOCKED.getValue();
    }

}
