package me.kafein.supercombat.common.expansion.loader;

import me.kafein.supercombat.common.expansion.Expansion;
import me.kafein.supercombat.common.expansion.classloader.ExpansionClassLoader;
import me.kafein.supercombat.common.expansion.exception.InvalidInfoException;
import me.kafein.supercombat.common.expansion.info.ExpansionInfo;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ExpansionLoader {

    public static Collection<Expansion> load() {
        Collection<Expansion> expansions = new HashSet<>();

        File parentFile = new File("plugins/SuperCombat/expansion");
        if (!parentFile.exists()) {
            parentFile.mkdirs();
            return expansions;
        }

        File[] files = parentFile.listFiles();
        if (files == null) return expansions;

        for (File file : files) {
            if (file.isDirectory() || !file.getName().endsWith(".jar")) continue;

            try {
                ExpansionClassLoader expansionClassLoader = new ExpansionClassLoader(file,
                        loadExpansionInfo(file),
                        Expansion.class.getClassLoader());
                Expansion expansion = expansionClassLoader.getExpansion();
                expansion.onEnable();
                expansions.add(expansion);
            } catch (Exception e) {
                throw new InvalidInfoException("Could not load expansion info for " + file.getName() + " \n Cause: " + e.getMessage());
            }
        }
        return expansions;
    }

    private static ExpansionInfo loadExpansionInfo(File file) throws Exception {
        JarFile jarFile = new JarFile(file);
        JarEntry entry = jarFile.getJarEntry("expansion.yml");

        InputStream inputStream = jarFile.getInputStream(entry);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Map<String, Object> map = new Yaml().load(reader);
        reader.close();
        inputStream.close();

        return new ExpansionInfo(
                (String) map.get("name"),
                (String) map.get("description"),
                (String) map.get("version"),
                (String) map.get("main"),
                (String) map.get("author")
        );
    }

}
