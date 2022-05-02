package me.kafein.common.expansion;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ExpansionManager {

    @Getter private final Map<String, Expansion> expansions = new ConcurrentHashMap<>();

    @SneakyThrows
    public ExpansionManager load(String dataFolder) {
        File file = new File(dataFolder + "/expansion");
        if (!file.exists()) {
            file.mkdirs();
            return this;
        }

        File[] files = file.listFiles();
        if (files == null) return this;

        for (File f : files) {
            if (f.isDirectory()) continue;
            if (!f.getName().endsWith(".jar")) continue;
            ExpansionLoader expansionLoader = new ExpansionLoader(f, Expansion.class.getClassLoader());
            Class<?> clazz = expansionLoader.findClass(Expansion.class);
            if (clazz == null) continue;
            Expansion expansion = (Expansion) clazz.newInstance();
            expansion.onEnable();
            expansions.put(expansion.getName(), expansion);
        }
        return this;
    }

    public void register(Expansion expansion) {
        expansions.put(expansion.getName(), expansion);
    }

    public void unregister(Expansion expansion) {
        expansions.remove(expansion.getName());
    }

    public boolean contains(String name) {
        return expansions.containsKey(name);
    }

    public Optional<Expansion> getExpansion(String name) {
        return Optional.ofNullable(expansions.get(name));
    }

}
