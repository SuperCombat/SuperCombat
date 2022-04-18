package me.kafein.supercombattag;

import lombok.Getter;
import me.kafein.supercombattag.tag.TagManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SuperCombatTag extends JavaPlugin {

    @Getter
    private static SuperCombatTag instance;

    @Getter
    private TagManager tagManager;

    @Override
    public void onEnable() {

        instance = this;

        tagManager = new TagManager();

    }

    @Override
    public void onDisable() {

    }

}
