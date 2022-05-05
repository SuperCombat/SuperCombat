package me.kafein.common.expansion;

public interface Expansion {

    void onEnable();

    void onDisable();

    void onConfigReload();

    String getName();

    String getVersion();

    String getDescription();

    String getAuthor();

}
