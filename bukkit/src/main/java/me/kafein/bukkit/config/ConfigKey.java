package me.kafein.bukkit.config;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ConfigKey<T> {

    private final String fileName;
    private final String property;
    private T key;

    public ConfigKey(String fileName, String property, T key) {
        this.fileName = fileName;
        this.property = property;
        this.key = key;
    }

}
