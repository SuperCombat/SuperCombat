package me.kafein.supercombat.common.config;

import lombok.Getter;
import me.kafein.supercombat.common.config.key.ConfigKeys;

public enum ConfigType {
    SETTINGS(ConfigKeys.Settings.class), LANGUAGE(ConfigKeys.Language.class);

    @Getter
    private final Class<?> clazz;

    ConfigType(Class<?> clazz) {
        this.clazz = clazz;
    }

}
