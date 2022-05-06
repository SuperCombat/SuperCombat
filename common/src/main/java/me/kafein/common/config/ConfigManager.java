package me.kafein.common.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ninja.leaping.configurate.ConfigurationNode;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class ConfigManager {

    private final ConfigLoader loader = new ConfigLoader();

    private final Map<String, ConfigurationNode> configs = new HashMap<>();

    public ConfigManager loadConfig(String configName, String dataFolder, Class<?> mainClass, Class<?> keyClass, boolean prefix) {
        ClassLoader classLoader = mainClass.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(dataFolder.substring(dataFolder.indexOf("/") + 1));
        try {
            ConfigurationNode config = loader.loadConfig(dataFolder, inputStream);
            if (prefix) loader.loadFields(config, keyClass.getDeclaredFields(), configName);
            else loader.loadFields(config, keyClass.getDeclaredFields());
            addConfig(configName, config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    @Nullable
    public ConfigurationNode getConfig(String name) {
        return configs.get(name);
    }

    public ConfigurationNode getConfig(ConfigType configType) {
        return configs.get(configType.name().toLowerCase(Locale.ROOT));
    }

    public void addConfig(String name, ConfigurationNode config) {
        configs.put(name, config);
    }

    public void removeConfig(String name) {
        configs.remove(name);
    }

    public boolean containsConfig(String name) {
        return configs.containsKey(name);
    }

}
