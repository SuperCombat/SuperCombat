package me.kafein.common.listener;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class ListenerRegisteror {

    @Getter private final static Set<Listener> listeners = new HashSet<>();

    public static void register(Listener listener) {
        listeners.add(listener);
    }

    public static void unregister(Listener listener) {
        listeners.remove(listener);
    }

    public static void unregisterAll() {
        listeners.clear();
    }

}
