package me.kafein.common;

import me.kafein.common.config.ConfigLoader;
import me.kafein.common.expansion.ExpansionManager;
import me.kafein.common.tag.TagManager;
import org.jetbrains.annotations.NotNull;

public interface SuperCombat {

    static SuperCombat getInstance() {
        return SuperCombatProvider.getSuperCombat();
    }

    @NotNull ConfigLoader getConfigLoader();

    @NotNull TagManager getTagManager();

    @NotNull ExpansionManager getExpansionManager();

}
