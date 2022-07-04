package me.kafein.supercombat.common.blocker.type;

import me.kafein.supercombat.common.blocker.Blocker;
import me.kafein.supercombat.common.config.key.ConfigKeys;

public class FlyBlocker extends Blocker {

    @Override
    public void init() {
        enabled = ConfigKeys.Settings.FLY_BLOCKER_ENABLED.getValue();
        notificationMessage = ConfigKeys.Language.FLY_IS_BLOCKED.getValue();
    }

}
