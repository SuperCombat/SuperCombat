package me.kafein.supercombat.common.config.node;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;
import org.yaml.snakeyaml.DumperOptions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class NodeLoader {

    /**
     * load a node with path
     *
     * @param path the path of the node
     * @return the node
     * @throws IOException if the node is not found
     */
    public static ConfigurationNode loadNode(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        ConfigurationLoader<ConfigurationNode> loader = YAMLConfigurationLoader
                .builder()
                .setFlowStyle(DumperOptions.FlowStyle.BLOCK)
                .setIndent(2)
                .setFile(file)
                .build();
        return loader.load();
    }

    /**
     * load a node with path
     *
     * @param path        the path of the node
     * @param inputStream the input stream
     * @return the node
     * @throws IOException if the node is not found
     */
    public static ConfigurationNode loadNode(String path, InputStream inputStream) throws IOException {
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

    public static ConfigurationNode loadNode(File file) throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        ConfigurationLoader<ConfigurationNode> loader = YAMLConfigurationLoader
                .builder()
                .setFlowStyle(DumperOptions.FlowStyle.BLOCK)
                .setIndent(2)
                .setFile(file)
                .build();
        return loader.load();
    }

}
