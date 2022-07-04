package me.kafein.supercombat.common.expansion.classloader;

import com.google.common.io.ByteStreams;
import lombok.Getter;
import me.kafein.supercombat.common.expansion.Expansion;
import me.kafein.supercombat.common.expansion.info.ExpansionInfo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

@Getter
public class ExpansionClassLoader extends URLClassLoader {

    private final Map<String, Class<?>> classes = new ConcurrentHashMap<>();

    private final JarFile jarFile;
    private final Manifest manifest;
    private final URL url;
    private final Expansion expansion;

    public ExpansionClassLoader(File file, ExpansionInfo expansionInfo, ClassLoader classLoader) throws Exception {
        super(new URL[]{file.toURI().toURL()}, classLoader);
        jarFile = new JarFile(file);
        this.manifest = jarFile.getManifest();
        this.url = file.toURI().toURL();

        Class<?> mainClass = Class.forName(expansionInfo.getMain(), true, this);
        expansion = mainClass
                .asSubclass(Expansion.class)
                .getConstructor(ExpansionInfo.class)
                .newInstance(expansionInfo);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> result = classes.get(name);

        if (result == null) {
            String path = name.replace('.', '/').concat(".class");
            JarEntry entry = jarFile.getJarEntry(path);

            if (entry != null) {
                byte[] classBytes;

                try (InputStream is = jarFile.getInputStream(entry)) {
                    classBytes = ByteStreams.toByteArray(is);
                } catch (IOException ex) {
                    throw new ClassNotFoundException(name, ex);
                }

                int dot = name.lastIndexOf('.');
                if (dot != -1) {
                    String pkgName = name.substring(0, dot);
                    if (getPackage(pkgName) == null) {
                        try {
                            if (manifest != null) {
                                definePackage(pkgName, manifest, url);
                            } else {
                                definePackage(pkgName, null, null, null, null, null, null, null);
                            }
                        } catch (IllegalArgumentException ex) {
                            if (getPackage(pkgName) == null) {
                                throw new IllegalStateException("Cannot find package " + pkgName);
                            }
                        }
                    }
                }

                CodeSigner[] signers = entry.getCodeSigners();
                CodeSource source = new CodeSource(url, signers);

                result = defineClass(name, classBytes, 0, classBytes.length, source);
            }

            if (result == null) {
                result = super.findClass(name);
            }

            classes.put(name, result);
        }

        return result;
    }

    @Override
    public URL getResource(final String name) {
        return findResource(name);
    }

    @Override
    public Enumeration<URL> getResources(final String name) throws IOException {
        return findResources(name);
    }

    @Override
    public void close() throws IOException {
        try {
            super.close();
        } finally {
            jarFile.close();
        }
    }

}

