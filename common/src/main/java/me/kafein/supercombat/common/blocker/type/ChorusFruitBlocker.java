package me.kafein.supercombat.common.blocker.type;

import me.kafein.supercombat.common.blocker.Blocker;
import me.kafein.supercombat.common.config.key.ConfigKeys;

public class ChorusFruitBlocker extends Blocker {

    @Override
    public void init() {
        enabled = ConfigKeys.Settings.CHORUSFRUIT_BLOCKER_ENABLED.getValue();
        notificationMessage = ConfigKeys.Language.CHORUSFRUIT_IS_BLOCKED.getValue();
    }

}
