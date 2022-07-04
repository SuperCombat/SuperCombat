package me.kafein.supercombat.bukkit.listener.adapter;

import me.kafein.supercombat.common.listener.ListenerRegistrar;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.Arrays;
import java.util.List;

public class ListenerAdapter {

    public static void registerBukkitListener(Plugin plugin, List<Class<?>> list) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        list.forEach(clazz -> {
            try {
                Listener listener = clazz
                        .asSubclass(Listener.class)
                        .newInstance();
                pluginManager.registerEvents(listener, plugin);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void registerBukkitListener(Plugin plugin, Class<?>... classes) {
        registerBukkitListener(plugin, Arrays.asList(classes));
    }

    public static void registerTagListener(List<Class<?>> list) {
        list.forEach(clazz -> {
            try {
                me.kafein.supercombat.common.listener.Listener listener = clazz
                        .asSubclass(me.kafein.supercombat.common.listener.Listener.class)
                        .newInstance();
                ListenerRegistrar.register(listener);
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void registerTagListener(Class<?>... classes) {
        registerTagListener(Arrays.asList(classes));
    }

}
