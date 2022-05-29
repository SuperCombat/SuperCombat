package me.kafein.supercombat.common.expansion.loader;

import me.kafein.supercombat.common.expansion.Expansion;
import me.kafein.supercombat.common.expansion.classloader.ExpansionClassLoader;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

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
                ExpansionClassLoader expansionClassLoader = new ExpansionClassLoader(f, Expansion.class.getClassLoader());
                Class<?> clazz = expansionClassLoader.findClass(Expansion.class);
                if (clazz == null) continue;
                Expansion expansion = (Expansion) clazz.getDeclaredConstructor(Expansion.class).newInstance();
                expansion.onEnable();
                expansions.add(expansion);
                expansionClassLoader.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return expansions;
    }

}
