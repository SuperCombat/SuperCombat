package me.kafein.common.expansion;

import lombok.SneakyThrows;

import java.io.File;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ExpansionLoader {

    @SneakyThrows
    public void load(String dataFolder) {
        File file = new File(dataFolder + "/expansion");
        if (!file.exists()) {
            file.mkdir();
            return;
        }
        File[] files = file.listFiles();
        if (files == null || files.length == 0) return;
        for (File f : files) {
            JarFile jarFile = new JarFile(f);
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                if (jarEntry.getName().endsWith(".class")) {
                    String className = jarEntry.getName().replaceAll("/", ".").replaceAll(".class", "");
                    Class<?> clazz = Class.forName(className);
                    for (Class<?> i : clazz.getInterfaces()) {
                        if (i.getName().equals(Expansion.class.getName()) && !clazz.isInterface()) {
                            Expansion expansion = (Expansion) clazz.newInstance();
                            expansion.onEnable();
                        }
                    }
                }
            }
        }

    }

}
