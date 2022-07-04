package me.kafein.supercombat.common.blocker;

import lombok.Getter;

import java.util.Set;

public abstract class Blocker {

    protected boolean enabled;
    protected Type type;
    protected Set<String> values;
    @Getter protected String notificationMessage;

    public abstract void init();

    public boolean control(String name) {
        if (!enabled || values == null) return true;
        switch (type) {
            case BLACKLIST: return !contains(name);
            case WHITELIST: return contains(name);
            default: return true;
        }
    }

    private boolean contains(String name) {
        return values.stream()
                .anyMatch(s -> s.equalsIgnoreCase(name) || s.startsWith(name));
    }

    public boolean isEnabled() {
        return enabled;
    }

    protected enum Type {
        WHITELIST, BLACKLIST;
    }

}
