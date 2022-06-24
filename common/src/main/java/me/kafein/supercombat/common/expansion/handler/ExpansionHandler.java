package me.kafein.supercombat.common.expansion.handler;

import lombok.Getter;
import me.kafein.supercombat.common.expansion.Expansion;
import me.kafein.supercombat.common.expansion.loader.ExpansionLoader;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ExpansionHandler {

    @Getter private static final Map<String, Expansion> expansions = new ConcurrentHashMap<>();

    public static void init() {
        ExpansionLoader.load().forEach(expansion -> {
            expansions.put(expansion.getName(), expansion);
        });
    }

    public static void enableAll() {
        expansions.values().forEach(Expansion::onEnable);
    }

    public static void disableAll() {
        expansions.values().forEach(Expansion::onDisable);
    }

    public static void reloadConfigAll() {
        expansions.values().forEach(Expansion::onReload);
    }

    public static void register(Expansion expansion) {
        expansion.onEnable();
        expansions.put(expansion.getName(), expansion);
    }

    public static void unregister(Expansion expansion) {
        expansion.onDisable();
        expansions.remove(expansion.getName());
    }

    public static Optional<Expansion> find(String name) {
        return Optional.ofNullable(expansions.get(name));
    }

}
