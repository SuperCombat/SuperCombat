package me.kafein.supercombat.common.blocker.type;

import me.kafein.supercombat.common.blocker.Blocker;
import me.kafein.supercombat.common.config.key.ConfigKeys;

public class InventoryBlocker extends Blocker {

    @Override
    public void init() {
        enabled = ConfigKeys.Settings.INVENTORY_BLOCKER_ENABLED.getValue();
        notificationMessage = ConfigKeys.Language.INVENTORY_IS_BLOCKED.getValue();
    }

}
