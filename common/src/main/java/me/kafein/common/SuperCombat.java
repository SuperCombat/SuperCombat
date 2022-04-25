package me.kafein.common;

import lombok.NonNull;
import me.kafein.common.config.ConfigLoader;
import me.kafein.common.tag.TagManager;

public interface SuperCombat {

    static SuperCombat getInstance() {
        return SuperCombatProvider.getSuperCombat();
    }

    @NonNull TagManager getTagManager();

    @NonNull ConfigLoader getConfigLoader();

}
