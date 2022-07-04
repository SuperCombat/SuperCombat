package me.kafein.supercombat.common.blocker.type;

import me.kafein.supercombat.common.blocker.Blocker;
import me.kafein.supercombat.common.config.key.ConfigKeys;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class WorldBlocker extends Blocker {

    @Override
    public void init() {
        enabled = ConfigKeys.Settings.WORLD_BLOCKER_ENABLED.getValue();
        type = Type.valueOf(ConfigKeys.Settings.WORLD_BLOCKER_TYPE.getValue().toUpperCase(Locale.ROOT));

        List<String> blockedWorlds = ConfigKeys.Settings.WORLD_BLOCKER_WORLDS.getValue();
        values = new HashSet<>(blockedWorlds);
    }


}
