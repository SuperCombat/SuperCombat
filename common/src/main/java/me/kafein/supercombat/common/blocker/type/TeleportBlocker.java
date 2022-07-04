package me.kafein.supercombat.common.blocker.type;

import me.kafein.supercombat.common.blocker.Blocker;
import me.kafein.supercombat.common.config.key.ConfigKeys;

public class TeleportBlocker extends Blocker {

    @Override
    public void init() {
        enabled = ConfigKeys.Settings.TELEPORT_BLOCKER_ENABLED.getValue();
        notificationMessage = ConfigKeys.Language.TELEPORT_IS_BLOCKED.getValue();
    }

}
