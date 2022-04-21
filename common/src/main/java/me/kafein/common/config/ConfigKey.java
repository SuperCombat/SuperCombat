package me.kafein.common.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigKey<V> {

    private final ConfigType configType;
    private final String[] path;
    private V value;

    public ConfigKey(V value, ConfigType configType, String... path) {
        this.value = value;
        this.configType = configType;
        this.path = path;
    }

}
