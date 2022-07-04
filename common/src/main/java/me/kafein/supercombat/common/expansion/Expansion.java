package me.kafein.supercombat.common.expansion;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.kafein.supercombat.common.expansion.info.ExpansionInfo;

@RequiredArgsConstructor
@Getter
public abstract class Expansion {

    private final ExpansionInfo info;

    public abstract void onEnable();
    public abstract void onDisable();

    public void onReload(){}

    public String getName() {
        return info.getName();
    }

    public String getDescription() {
        return info.getDescription();
    }

    public String getVersion() {
        return info.getVersion();
    }

    public String getAuthor() {
        return info.getAuthor();
    }

}
