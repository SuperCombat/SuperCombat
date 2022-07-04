package me.kafein.supercombat.common.blocker.type.item;

import me.kafein.supercombat.common.blocker.Blocker;
import me.kafein.supercombat.common.config.key.ConfigKeys;

public class ItemPickupBlocker extends Blocker {

    @Override
    public void init() {
        enabled = ConfigKeys.Settings.ITEM_PICKUP_BLOCKER_ENABLED.getValue();
        notificationMessage = ConfigKeys.Language.ITEM_PICKUP_IS_BLOCKED.getValue();
    }

}
