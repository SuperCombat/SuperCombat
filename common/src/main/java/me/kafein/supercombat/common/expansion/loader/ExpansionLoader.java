package me.kafein.supercombat.common.expansion.loader;

import me.kafein.supercombat.common.expansion.Expansion;
import me.kafein.supercombat.common.expansion.classloader.ExpansionClassLoader;
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
        File file = new File("plugins/SuperCombat/expansion");
        if (!file.exists()) {
            file.mkdirs();
            return expansions;
        }
        File[] files = file.listFiles();
        if (files == null) return expansions;
        try {
            for (File f : files) {
                if (f.isDirectory()) continue;
                if (!f.getName().endsWith(".jar")) continue;
                JarFile jarFile = new JarFile(f);
                ExpansionClassLoader expansionClassLoader = new ExpansionClassLoader(
                        f,
                        loadExpansionInfo(jarFile),
                        Expansion.class.getClassLoader());
                Class<?> clazz = expansionClassLoader.findClass(Expansion.class);
                if (clazz == null) continue;
                Expansion expansion = (Expansion) clazz.getDeclaredConstructor(Expansion.class).newInstance();
                expansion.onEnable();
                expansions.add(expansion);
                expansionClassLoader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expansions;
    }

    private static ExpansionInfo loadExpansionInfo(JarFile jarFile) {
        JarEntry entry = jarFile.getJarEntry("expansion.yml");
        if (entry == null) {
            throw new IllegalStateException("Expansion file is missing expansion.yml");
        }
        try {
            InputStream inputStream = jarFile.getInputStream(entry);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Map<String, Object> map = new Yaml().load(reader);
            ExpansionInfo expansionInfo = new ExpansionInfo(
                    (String) map.get("name"),
                    (String) map.get("description"),
                    (String) map.get("version"),
                    (String) map.get("mainClass"),
                    (String[]) map.get("author"));
            reader.close();
            inputStream.close();
            return expansionInfo;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
