package me.kafein.bukkit;

import lombok.Getter;
import me.kafein.common.SuperCombatTag;
import me.kafein.common.SuperCombatTagProvider;
import me.kafein.common.tag.TagManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SuperCombatTagPlugin extends JavaPlugin implements SuperCombatTag {

    @Getter
    private static SuperCombatTag instance;

    @Getter
    private TagManager tagManager;

    @Override
    public void onEnable() {

        instance = this;
        SuperCombatTagProvider.setSuperCombatTag(this);

        tagManager = new TagManager();

    }

    @Override
    public void onDisable() {

    }

}
