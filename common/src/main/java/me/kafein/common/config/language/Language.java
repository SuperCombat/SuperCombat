package me.kafein.common.config.language;

public enum Language {
    EN, TR;

    public static boolean contains(String language) {
        for (Language l : Language.values()) {
            if (l.name().equals(language)) {
                return true;
            }
        }
        return false;
    }

}
