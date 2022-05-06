package me.kafein.common;

import me.kafein.common.config.ConfigManager;
import me.kafein.common.expansion.ExpansionManager;
import me.kafein.common.tag.TagManager;
import org.jetbrains.annotations.NotNull;

public interface SuperCombat {

    static SuperCombat getInstance() {
        return SuperCombatProvider.getSuperCombat();
    }

    @NotNull ConfigManager getConfigManager();

    @NotNull TagManager getTagManager();

    @NotNull ExpansionManager getExpansionManager();

}
