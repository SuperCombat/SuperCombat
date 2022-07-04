package me.kafein.supercombat.common.blocker.type.block;

import me.kafein.supercombat.common.blocker.Blocker;
import me.kafein.supercombat.common.config.key.ConfigKeys;

public class BlockPlaceBlocker extends Blocker {

    @Override
    public void init() {
        enabled = ConfigKeys.Settings.BLOCK_PLACE_BLOCKER_ENABLED.getValue();
        notificationMessage = ConfigKeys.Language.BLOCK_PLACE_IS_BLOCKED.getValue();
    }

}
