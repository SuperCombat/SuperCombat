package me.kafein.supercombat.common.config.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.kafein.supercombat.common.config.Config;
import me.kafein.supercombat.common.config.ConfigType;
import me.kafein.supercombat.common.config.key.ConfigKey;
import me.kafein.supercombat.common.config.key.ConfigKeys;
import me.kafein.supercombat.common.config.node.NodeLoader;
import ninja.leaping.configurate.ConfigurationNode;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
@SuppressWarnings({"unchecked"})
public class ConfigHandler {

    private static final Map<String, Config> configs = new HashMap<>();

    /**
     * Loads config from file and puts it to configs map.
     *
     * if config is already loaded, it will be reloaded.
     */
    public static void init() {
        ClassLoader classLoader = ConfigHandler.class.getClassLoader();
        ConfigHandler.createConfig("settings",
                "settings.yml",
                classLoader.getResourceAsStream("settings.yml")).ifPresent(config -> {
            ConfigHandler.put(ConfigType.SETTINGS, config);
            ConfigHandler.putNodesToClass(config, ConfigType.SETTINGS.getClazz(), true);
        });

        String language = ConfigKeys.Settings.LANGUAGE.getValue();
        InputStream inputStream = classLoader.getResourceAsStream("language/" + language + ".yml");
        if (inputStream == null) inputStream = classLoader.getResourceAsStream("language/language_en.yml");
        ConfigHandler.createConfig("language",
                "language/language_" + language + ".yml",
                inputStream).ifPresent(config -> {
            ConfigHandler.put(ConfigType.LANGUAGE, config);
            ConfigHandler.putNodesToClass(config, ConfigType.LANGUAGE.getClazz(), true);
        });
    }


    /**
     * Put config's nodes into a class's fields
     *
     * @param config config to put nodes into fields
     * @param clazz  class to put nodes into fields
     * @param prefix prefix to put nodes into fields
     * @param <V>    type of the class
     */
    public static <V> void putNodesToClass(Config config, Class<?> clazz, boolean prefix) {
        try {
            for (Field field : clazz.getDeclaredFields()) {
                ConfigKey<V> configKey = (ConfigKey<V>) field.get(null);
                ConfigurationNode node = config.getNode();
                if (prefix) node = node.getNode(config.getName().toLowerCase(Locale.ROOT));
                for (String path : configKey.getPath()) node = node.getNode(path);
                V value = (V) node.getValue();
                if (value == null) continue;
                configKey.setValue(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a config from a path and input stream
     *
     * @param name        name of the config
     * @param path        path of the config
     * @param inputStream input stream of the config
     * @return config
     */
    public static Optional<Config> createConfig(String name, String path, InputStream inputStream) {
        path = "plugins/SuperCombat/" + path;
        try {
            return Optional.of(new Config(name, path, NodeLoader.loadNode(path, inputStream)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Create a config from a path and input stream
     *
     * @param name name of the config
     * @param path path of the config
     * @return config
     */
    public static Optional<Config> createConfig(String name, String path) {
        path = "plugins/SuperCombat/" + path;
        try {
            return Optional.of(new Config(name, path, NodeLoader.loadNode(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Get a config from a config type
     *
     * @param name name of the config
     * @return config
     */
    public static Optional<Config> find(String name) {
        return Optional.ofNullable(configs.get(name.toLowerCase(Locale.ROOT)));
    }

    /**
     * Get a config from a config type
     *
     * @param configType config type
     * @return config
     */
    public static Optional<Config> find(ConfigType configType) {
        return find(configType.name());
    }

    /**
     * Add a config to the map
     *
     * @param name   name of the config
     * @param config config
     */
    public static void put(String name, Config config) {
        configs.put(name.toLowerCase(Locale.ROOT), config);
    }

    /**
     * Add a config to the map
     *
     * @param configType config type
     * @param config     config
     */
    public static void put(ConfigType configType, Config config) {
        configs.put(configType.name().toLowerCase(Locale.ROOT), config);
    }

    /**
     * Remove a config from the map
     *
     * @param name name of the config
     */
    public static void remove(String name) {
        configs.remove(name.toLowerCase(Locale.ROOT));
    }

}
