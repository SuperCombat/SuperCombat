package me.kafein.common.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigKey<T> {

    private final String configType;
    private final String[] path;
    private T key;

    public ConfigKey(T key, String configType, String... path) {
        this.key = key;
        this.configType = configType;
        this.path = path;
    }

}
