package me.kafein.supercombat.common.blocker.type.block;

import me.kafein.supercombat.common.blocker.Blocker;
import me.kafein.supercombat.common.config.key.ConfigKeys;

public class BlockBreakBlocker extends Blocker {

    @Override
    public void init() {
        enabled = ConfigKeys.Settings.BLOCK_BREAK_BLOCKER_ENABLED.getValue();
        notificationMessage = ConfigKeys.Language.BLOCK_BREAK_IS_BLOCKED.getValue();
    }

}

