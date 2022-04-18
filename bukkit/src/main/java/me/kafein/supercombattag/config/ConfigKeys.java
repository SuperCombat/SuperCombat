package me.kafein.supercombattag.config;

public class ConfigKeys {

    public static final ConfigKey<String> LANGUAGE = new ConfigKey<>("settings", "language", "en");

    public static final ConfigKey<String> PREFIX = new ConfigKey<>("language", "prefix", "&8[&6SuperCombatTag&8]&r ");

    public static final ConfigKey<Boolean> MOB_TAGGING = new ConfigKey<>("settings", "mob-tagging", false);

    public static final ConfigKey<Integer> TAG_DURATION = new ConfigKey<>("settings", "tag-duration", 30);

}
