package me.kafein.common.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.kafein.common.SuperCombat;
import me.kafein.common.config.language.Language;
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

    public void loadDefaultConfigs(String dataFolder) {
        Class<?> clazz = SuperCombat.class;
        for (ConfigType configType : ConfigType.values()) {
            ClassLoader classLoader = clazz.getClassLoader();
            String filePath = null;
            InputStream inputStream = null;
            switch (configType) {
                case SETTINGS:
                    filePath = dataFolder + "/settings.yml";
                    inputStream = classLoader.getResourceAsStream("settings.yml");
                    break;
                case LANGUAGE:
                    String language = getConfig(ConfigType.SETTINGS).getNode("settings", "language").getString();
                    if (language == null || Language.of(language.toUpperCase(Locale.ROOT)) == null) {
                        language = "en";
                    }
                    filePath = dataFolder + "/language/language_" + language + ".yml";
                    inputStream = classLoader.getResourceAsStream("language/language_" + language + ".yml");
                    break;
            }
            try {
                ConfigurationNode config = loader.loadConfig(filePath, inputStream);
                loader.loadFields(config, configType.getClazz().getDeclaredFields(), configType.name().toLowerCase(Locale.ROOT));
                addConfig(configType.name().toLowerCase(Locale.ROOT), config);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
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
