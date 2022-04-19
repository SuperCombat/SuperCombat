package me.kafein.common;

import lombok.NonNull;
import me.kafein.common.tag.TagManager;

public interface SuperCombatTag {

    static SuperCombatTag getInstance() {
        return SuperCombatTagProvider.getSuperCombatTag();
    }

    @NonNull TagManager getTagManager();

}
