package me.kafein.supercombat.common.blocker.type;

import me.kafein.supercombat.common.blocker.Blocker;
import me.kafein.supercombat.common.config.key.ConfigKeys;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class CommandBlocker extends Blocker {

    @Override
    public void init() {
        enabled = ConfigKeys.Settings.COMMAND_BLOCKER_ENABLED.getValue();
        type = Type.valueOf(ConfigKeys.Settings.COMMAND_BLOCKER_TYPE.getValue().toUpperCase(Locale.ROOT));
        notificationMessage = ConfigKeys.Language.COMMAND_IS_BLOCKED.getValue();

        List<String> blockedCommands = ConfigKeys.Settings.COMMAND_BLOCKER_COMMANDS.getValue();
        values = new HashSet<>(blockedCommands);
    }

}
