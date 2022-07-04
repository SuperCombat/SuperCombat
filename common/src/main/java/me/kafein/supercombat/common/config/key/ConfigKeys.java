package me.kafein.supercombat.common.config.key;

import com.google.common.collect.ImmutableList;
import me.kafein.supercombat.common.config.ConfigType;

import java.util.List;

public final class ConfigKeys {
    private ConfigKeys() {}

    public static final class Settings {
        private Settings() {}

        public static final ConfigKey<String> LANGUAGE = new ConfigKey<>("en", ConfigType.SETTINGS, "language");

        public static final ConfigKey<String> ADMIN_PERM = new ConfigKey<>("supercombat.admin", ConfigType.SETTINGS, "admin-permission");

        public static final ConfigKey<Boolean> MOB_TAGGING = new ConfigKey<>(false, ConfigType.SETTINGS, "mob-tagging");
        public static final ConfigKey<Boolean> PROJECTILE_TAGGING = new ConfigKey<>(true, ConfigType.SETTINGS, "projectile-tagging");

        public static final ConfigKey<Integer> TAG_DURATION = new ConfigKey<>(30, ConfigType.SETTINGS, "tag-duration");

        public static final ConfigKey<Boolean> ONLY_TAGGING_SELF = new ConfigKey<>(false, ConfigType.SETTINGS, "only-tagging-self");

        public static final ConfigKey<Boolean> BYPASS_TAGGING_ENABLED = new ConfigKey<>(true, ConfigType.SETTINGS, "bypass-tagging", "enabled");
        public static final ConfigKey<Boolean> BYPASS_TAGGING_FOR_CREATIVE = new ConfigKey<>(true, ConfigType.SETTINGS, "bypass-tagging", "creative-bypass");

        public static final ConfigKey<Boolean> QUIT_PUNISHMENT = new ConfigKey<>(true, ConfigType.SETTINGS, "quit-punishment", "enabled");
        public static final ConfigKey<Boolean> KICK_PUNISHMENT = new ConfigKey<>(true, ConfigType.SETTINGS, "quit-punishment", "kick-enabled");
        public static final ConfigKey<List<String>> PUNISHMENT_COMMANDS = new ConfigKey<>(ImmutableList.of(), ConfigType.SETTINGS, "quit-punishment", "commands");

        public static final ConfigKey<Boolean> DEATH_UNTAGGING_SELF = new ConfigKey<>(true, ConfigType.SETTINGS, "death-untagging", "self");
        public static final ConfigKey<Boolean> DEATH_UNTAGGING_ENEMY = new ConfigKey<>(false, ConfigType.SETTINGS, "death-untagging", "enemy");

        public static final ConfigKey<Boolean> INVENTORY_BLOCKER_ENABLED = new ConfigKey<>(false, ConfigType.SETTINGS, "blockers", "inventory-blocker");
        public static final ConfigKey<Boolean> FLY_BLOCKER_ENABLED = new ConfigKey<>(true, ConfigType.SETTINGS, "blockers", "fly-blocker");
        public static final ConfigKey<Boolean> BLOCK_BREAK_BLOCKER_ENABLED = new ConfigKey<>(false, ConfigType.SETTINGS, "blockers", "block-break-blocker");
        public static final ConfigKey<Boolean> BLOCK_PLACE_BLOCKER_ENABLED = new ConfigKey<>(false, ConfigType.SETTINGS, "blockers", "block-place-blocker");
        public static final ConfigKey<Boolean> ITEM_DROP_BLOCKER_ENABLED = new ConfigKey<>(false, ConfigType.SETTINGS, "blockers", "item-drop-blocker");
        public static final ConfigKey<Boolean> ITEM_PICKUP_BLOCKER_ENABLED = new ConfigKey<>(false, ConfigType.SETTINGS, "blockers", "item-pickup-blocker");
        public static final ConfigKey<Boolean> ELYTRA_BLOCKER_ENABLED = new ConfigKey<>(true, ConfigType.SETTINGS, "blockers", "elytra-blocker");
        public static final ConfigKey<Boolean> CHORUSFRUIT_BLOCKER_ENABLED = new ConfigKey<>(true, ConfigType.SETTINGS, "blockers", "chorusfruit-blocker");

        public static final ConfigKey<Boolean> TELEPORT_BLOCKER_ENABLED = new ConfigKey<>(true, ConfigType.SETTINGS, "blockers", "teleport-blocker", "enabled");
        public static final ConfigKey<Boolean> PLUGIN_TELEPORT_ENABLED = new ConfigKey<>(false, ConfigType.SETTINGS, "blockers", "teleport-blocker", "plugin-teleport");
        public static final ConfigKey<Boolean> TELEPORT_UNTAGGING = new ConfigKey<>(true, ConfigType.SETTINGS, "blockers", "teleport-blocker", "teleport-untagging");

        public static final ConfigKey<Boolean> COMMAND_BLOCKER_ENABLED = new ConfigKey<>(false, ConfigType.SETTINGS, "blockers", "command-blocker", "enabled");
        public static final ConfigKey<String> COMMAND_BLOCKER_TYPE = new ConfigKey<>("blacklist", ConfigType.SETTINGS, "blockers", "command-blocker", "type");
        public static final ConfigKey<List<String>> COMMAND_BLOCKER_COMMANDS = new ConfigKey<>(ImmutableList.of(), ConfigType.SETTINGS, "blockers", "command-blocker", "commands");

        public static final ConfigKey<Boolean> WORLD_BLOCKER_ENABLED = new ConfigKey<>(false, ConfigType.SETTINGS, "blockers", "world-blocker", "enabled");
        public static final ConfigKey<String> WORLD_BLOCKER_TYPE = new ConfigKey<>("whitelist", ConfigType.SETTINGS, "blockers", "world-blocker", "type");
        public static final ConfigKey<List<String>> WORLD_BLOCKER_WORLDS = new ConfigKey<>(ImmutableList.of(), ConfigType.SETTINGS, "blockers", "world-blocker", "worlds");

        public static final ConfigKey<Boolean> TAGGING_COMMANDS_ENABLED = new ConfigKey<>(false, ConfigType.SETTINGS, "command-execution", "tagging", "enabled");
        public static final ConfigKey<List<String>> TAGGING_COMMANDS = new ConfigKey<>(ImmutableList.of(), ConfigType.SETTINGS, "command-execution", "tagging", "commands");

        public static final ConfigKey<Boolean> UNTAGGING_COMMANDS_ENABLED = new ConfigKey<>(false, ConfigType.SETTINGS, "command-execution", "untagging", "enabled");
        public static final ConfigKey<List<String>> UNTAGGING_COMMANDS = new ConfigKey<>(ImmutableList.of(), ConfigType.SETTINGS, "command-execution", "untagging", "commands");

        public static final ConfigKey<Boolean> RETAGGING_COMMANDS_ENABLED = new ConfigKey<>(false, ConfigType.SETTINGS, "command-execution", "retagging", "enabled");
        public static final ConfigKey<List<String>> RETAGGING_COMMANDS = new ConfigKey<>(ImmutableList.of(), ConfigType.SETTINGS, "command-execution", "retagging", "commands");

    }

    public static final class Language {
        private Language() {}

        public static final ConfigKey<String> PREFIX = new ConfigKey<>("&6SuperCombat &8➜", ConfigType.LANGUAGE, "prefix");

        public static final ConfigKey<String> RELOADED = new ConfigKey<>("&aSuccessfully reloaded the plugin.", ConfigType.LANGUAGE, "reloaded");

        public static final ConfigKey<String> NO_PERMISSION = new ConfigKey<>("&cYou don't have permission to use this command.", ConfigType.LANGUAGE, "no-permission");
        public static final ConfigKey<String> NO_PLAYER = new ConfigKey<>("&cYou must be a player to use this command.", ConfigType.LANGUAGE, "no-player");
        public static final ConfigKey<String> PLAYER_NOT_FOUND = new ConfigKey<>("&e%player% &7is offline.", ConfigType.LANGUAGE, "player-not-found");

        public static final ConfigKey<List<String>> HELP_MESSAGE = new ConfigKey<>(ImmutableList.of(), ConfigType.LANGUAGE, "help-message");

        public static final ConfigKey<String> UNKNOWN_CAUSE_TAGGED = new ConfigKey<>("You are tagged for because of an unknown cause.", ConfigType.LANGUAGE, "tag-messages", "unknown-cause");
        public static final ConfigKey<String> ATTACKER_TAGGED = new ConfigKey<>("You are tagged because of an attacker.", ConfigType.LANGUAGE, "tag-messages", "attacker-tagged");
        public static final ConfigKey<String> DEFENDER_TAGGED = new ConfigKey<>("You are tagged because of a defender.", ConfigType.LANGUAGE, "tag-messages", "defender-tagged");
        public static final ConfigKey<String> UNTAGGED = new ConfigKey<>("You are no longer tagged.", ConfigType.LANGUAGE, "tag-messages", "untagged");

        public static final ConfigKey<String> INVENTORY_IS_BLOCKED = new ConfigKey<>("&cYou can't open your inventory while you are tagged!", ConfigType.LANGUAGE, "blocker-messages", "inventory-is-blocked");
        public static final ConfigKey<String> FLY_IS_BLOCKED = new ConfigKey<>("&cYou can't fly while you are tagged!", ConfigType.LANGUAGE, "blocker-messages", "fly-is-blocked");
        public static final ConfigKey<String> TELEPORT_IS_BLOCKED = new ConfigKey<>("&cYou can't teleport while you are tagged!", ConfigType.LANGUAGE, "blocker-messages", "teleport-is-blocked");
        public static final ConfigKey<String> COMMAND_IS_BLOCKED = new ConfigKey<>("&cYou can't use this command while you are tagged!", ConfigType.LANGUAGE, "blocker-messages", "command-is-blocked");
        public static final ConfigKey<String> BLOCK_BREAK_IS_BLOCKED = new ConfigKey<>("&cYou can't break blocks while you are tagged!", ConfigType.LANGUAGE, "blocker-messages", "block-break-is-blocked");
        public static final ConfigKey<String> BLOCK_PLACE_IS_BLOCKED = new ConfigKey<>("&cYou can't place blocks while you are tagged!", ConfigType.LANGUAGE, "blocker-messages", "block-place-is-blocked");
        public static final ConfigKey<String> ITEM_DROP_IS_BLOCKED = new ConfigKey<>("&cYou can't drop items while you are tagged!", ConfigType.LANGUAGE, "blocker-messages", "item-drop-is-blocked");
        public static final ConfigKey<String> ITEM_PICKUP_IS_BLOCKED = new ConfigKey<>("&cYou can't pick up items while you are tagged!", ConfigType.LANGUAGE, "blocker-messages", "item-pickup-is-blocked");
        public static final ConfigKey<String> ELYTRA_IS_BLOCKED = new ConfigKey<>("&cYou can't use elytra while you are tagged!", ConfigType.LANGUAGE, "blocker-messages", "elytra-is-blocked");
        public static final ConfigKey<String> CHORUSFRUIT_IS_BLOCKED = new ConfigKey<>("&cYou can't eat chorus fruit while you are tagged!", ConfigType.LANGUAGE, "blocker-messages", "chorusfruit-is-blocked");

    }

}
