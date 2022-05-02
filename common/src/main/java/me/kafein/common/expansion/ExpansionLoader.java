package me.kafein.common.expansion;

import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class ExpansionLoader extends URLClassLoader {

    public ExpansionLoader(File file, ClassLoader classLoader) throws MalformedURLException {
        super(new URL[]{file.toURI().toURL()}, classLoader);
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
                } catch (NoClassDefFoundError e) {
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

