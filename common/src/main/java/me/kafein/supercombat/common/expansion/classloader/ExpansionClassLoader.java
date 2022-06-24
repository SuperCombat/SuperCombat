package me.kafein.supercombat.common.expansion.classloader;

import lombok.Getter;
import me.kafein.supercombat.common.expansion.Expansion;
import me.kafein.supercombat.common.expansion.info.ExpansionInfo;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

@Getter
public class ExpansionClassLoader extends URLClassLoader {

    private final JarFile jarFile;
    private final Manifest manifest;
    private final URL url;
    private final Expansion expansion;

    public ExpansionClassLoader(File file, ExpansionInfo expansionInfo, ClassLoader classLoader) throws Exception {
        super(new URL[]{file.toURI().toURL()}, classLoader);
        jarFile = new JarFile(file);
        this.manifest = jarFile.getManifest();
        this.url = file.toURI().toURL();

        Class<?> mainClass = Class.forName(expansionInfo.getMainClass(), true, this);
        if (!mainClass.isAssignableFrom(Expansion.class)) {
            throw new Exception("Main class " + expansionInfo.getMainClass() + " is not assignable from " + Expansion.class.getName());
        }
        expansion = mainClass
                .asSubclass(Expansion.class)
                .getConstructor(ExpansionInfo.class)
                .newInstance(expansionInfo);
    }

    @Nullable
    public <T> Class<? extends T> findClass(Class<T> clazz) throws IOException, ClassNotFoundException {

        final URL jar = getURLs()[0];
        final List<String> matches = new ArrayList<>();
        final List<Class<? extends T>> classes = new ArrayList<>();

        try (JarInputStream stream = new JarInputStream(jar.openStream())) {
            JarEntry entry;
            while ((entry = stream.getNextJarEntry()) != null) {
                final String name = entry.getName();
                if (!name.endsWith(".class")) {
                    continue;
                }
                matches.add(name.substring(0, name.lastIndexOf('.')).replace('/', '.'));
            }
            for (final String match : matches) {
                try {
                    final Class<?> loaded = loadClass(match);
                    if (clazz.isAssignableFrom(loaded)) {
                        classes.add(loaded.asSubclass(clazz));
                    }
                } catch (NoClassDefFoundError ignored) {
                }
            }
        }
        if (classes.isEmpty()) {
            close();
            return null;
        }
        return classes.get(0);
    }

}

