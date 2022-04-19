package me.kafein.common.config;

public class ConfigKeys {

    public static final ConfigKey<String> LANGUAGE = new ConfigKey<>("en", ConfigType.SETTINGS, "language");

    public static final ConfigKey<Boolean> MOB_TAGGING = new ConfigKey<>(false, ConfigType.SETTINGS, "mob-tagging");

    public static final ConfigKey<Integer> TAG_DURATION = new ConfigKey<>(30, ConfigType.SETTINGS, "tag-duration");


    public static final ConfigKey<String> PREFIX = new ConfigKey<>("&8[&6SuperCombatTag&8]&r ", ConfigType.LANGUAGE, "prefix");

}
