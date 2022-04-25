package me.kafein.bukkit.listener.adapter;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class BukkitListenerAdapter {

    public static boolean register(Plugin plugin, Class<?>... listenerClasses) {
        try {
            PluginManager pluginManager = plugin.getServer().getPluginManager();
            for (Class<?> listenerClass : listenerClasses) {
                Listener listener = (Listener) listenerClass.cast(listenerClass.newInstance());
                pluginManager.registerEvents(listener, plugin);
            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
