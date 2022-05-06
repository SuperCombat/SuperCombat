package me.kafein.common.config;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;
import org.yaml.snakeyaml.DumperOptions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;

public class ConfigLoader {

    public ConfigurationNode loadConfig(String path, InputStream inputStream) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            OutputStream out = Files.newOutputStream(file.toPath());
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
        return loader.load();
    }

    public <T> ConfigLoader loadFields(ConfigurationNode node, Field[] fields, String prefix) throws IllegalArgumentException, IllegalAccessException {
        for (Field field : fields) {
            ConfigKey<T> configKey = (ConfigKey<T>) field.get(null);
            node = node.getNode(prefix);
            for (String path : configKey.getPath()) {
                node = node.getNode(path);
            }
            T value = (T) node.getValue();
            if (value == null) continue;
            configKey.setValue(value);
        }
        return this;
    }

    public <T> ConfigLoader loadFields(ConfigurationNode node, Field[] fields) throws IllegalArgumentException, IllegalAccessException {
        for (Field field : fields) {
            ConfigKey<T> configKey = (ConfigKey<T>) field.get(null);
            for (String path : configKey.getPath()) {
                node = node.getNode(path);
            }
            T value = (T) node.getValue();
            if (value == null) continue;
            configKey.setValue(value);
        }
        return this;
    }

}
