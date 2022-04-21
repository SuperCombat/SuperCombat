package me.kafein.common.config;

import lombok.Getter;
import lombok.SneakyThrows;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;
import org.yaml.snakeyaml.DumperOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Locale;

public class ConfigLoader {

    @Getter
    private static ConfigLoader instance;

    public ConfigLoader() {
        instance = this;
    }

    @SneakyThrows
    public ConfigLoader loadConfig(ConfigType configType, Class<?> clazz, String dataFolder) {
        ClassLoader classLoader = clazz.getClassLoader();
        File file = null;
        InputStream inputStream = null;
        switch (configType) {
            case SETTINGS:
                file = new File(dataFolder + "/settings.yml");
                inputStream = classLoader.getResourceAsStream("settings.yml");
                break;
            case LANGUAGE:
                String language = ConfigType.SETTINGS.getConfigurationNode().getNode("settings", "language").getString();
                if (language == null || !(language.equalsIgnoreCase("EN") || language.equalsIgnoreCase("TR")))
                    language = "en";
                file = new File(dataFolder + "/language/language_" + language + ".yml");
                inputStream = classLoader.getResourceAsStream("language/language_" + language + ".yml");
                break;
        }
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            inputStream.close();
        }
        ConfigurationLoader<ConfigurationNode> loader = YAMLConfigurationLoader
                .builder()
                .setFlowStyle(DumperOptions.FlowStyle.BLOCK)
                .setIndent(2)
                .setFile(file)
                .build();
        configType.setConfigurationNode(loader.load());

        return this;
    }

    @SneakyThrows
    public <T> ConfigLoader loadFields() {
        for (Field field : ConfigKeys.class.getDeclaredFields()) {
            ConfigKey<T> configKey = (ConfigKey<T>) field.get(null);
            ConfigType configType = ConfigType.valueOf(configKey.getConfigType().toUpperCase(Locale.ENGLISH));
            ConfigurationNode configurationNode = configType.getConfigurationNode();
            if (configurationNode == null) throw new NullPointerException("ConfigurationNode is null");
            configurationNode = configurationNode.getNode(configKey.getConfigType());
            for (String path : configKey.getPath()) configurationNode = configurationNode.getNode(path);
            T value = (T) configurationNode.getValue();
            if (value != null) configKey.setKey((T) configurationNode.getValue());
        }
        return this;
    }

}
