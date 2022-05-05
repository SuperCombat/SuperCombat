package me.kafein.common.config;

import com.google.common.collect.ImmutableList;

import java.util.List;

public final class ConfigKeys {
    private ConfigKeys() {}

    public static final class Settings {
        private Settings() {}

        public static final ConfigKey<String> LANGUAGE = new ConfigKey<>("en", ConfigType.SETTINGS, "language");

        public static final ConfigKey<String> ADMIN_PERM = new ConfigKey<>("sct.admin", ConfigType.SETTINGS, "admin-permission");

        public static final ConfigKey<Boolean> MOB_TAGGING = new ConfigKey<>(false, ConfigType.SETTINGS, "mob-tagging");

        public static final ConfigKey<Integer> TAG_DURATION = new ConfigKey<>(30, ConfigType.SETTINGS, "tag-duration");

        public static final ConfigKey<Boolean> QUIT_PUNISHMENT = new ConfigKey<>(false, ConfigType.SETTINGS, "quit-punishment", "enabled");
        public static final ConfigKey<Boolean> KICK_PUNISHMENT = new ConfigKey<>(false, ConfigType.SETTINGS, "quit-punishment", "kick-enabled");
        public static final ConfigKey<List<String>> PUNISHMENT_COMMANDS = new ConfigKey<>(ImmutableList.of(), ConfigType.SETTINGS, "quit-punishment", "punishment-commands");

        public static final ConfigKey<Boolean> DEATH_UNTAGGING_SELF = new ConfigKey<>(true, ConfigType.SETTINGS, "death-untagging", "self");
        public static final ConfigKey<Boolean> DEATH_UNTAGGING_ENEMY = new ConfigKey<>(true, ConfigType.SETTINGS, "death-untagging", "enemy");

        public static final ConfigKey<Boolean> DISABLE_TELEPORT = new ConfigKey<>(false, ConfigType.SETTINGS, "disable-teleport");
    }

    public static final class Language {
        private Language() {}

        public static final ConfigKey<String> PREFIX = new ConfigKey<>("&8[&6SuperCombatTag&8]&r ", ConfigType.LANGUAGE, "prefix");

        public static final ConfigKey<String> NO_PERMISSION = new ConfigKey<>("&cYou can't have permission to use this command!", ConfigType.LANGUAGE, "no-permission");

        public static final ConfigKey<String> NO_PLAYER = new ConfigKey<>("&cYou must be a player to use this command!", ConfigType.LANGUAGE, "no-player");

        public static final ConfigKey<List<String>> HELP_MESSAGE = new ConfigKey<>(ImmutableList.of(), ConfigType.LANGUAGE, "help-message");

    }

}
