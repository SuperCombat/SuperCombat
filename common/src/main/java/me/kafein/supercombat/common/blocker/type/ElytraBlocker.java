package me.kafein.supercombat.common.blocker.type;

import me.kafein.supercombat.common.blocker.Blocker;
import me.kafein.supercombat.common.config.key.ConfigKeys;

public class ElytraBlocker extends Blocker {

    @Override
    public void init() {
        enabled = ConfigKeys.Settings.ELYTRA_BLOCKER_ENABLED.getValue();
        notificationMessage = ConfigKeys.Language.ELYTRA_IS_BLOCKED.getValue();
    }

}
