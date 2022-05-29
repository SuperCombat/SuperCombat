package me.kafein.supercombat.common.config.language;

import org.jetbrains.annotations.Nullable;

public enum Language {
    EN, TR;

    @Nullable
    public static Language of(String name) {
        for (Language language : Language.values()) {
            if (language.name().equalsIgnoreCase(name)) {
                return language;
            }
        }
        return null;
    }

}
