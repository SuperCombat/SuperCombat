package me.kafein.bukkit;

import co.aikar.commands.BukkitCommandManager;
import lombok.Getter;
import me.kafein.bukkit.command.CombatTagCMD;
import me.kafein.bukkit.listener.DamageListener;
import me.kafein.common.SuperCombatTag;
import me.kafein.common.SuperCombatTagProvider;
import me.kafein.common.config.ConfigLoader;
import me.kafein.common.expansion.ExpansionLoader;
import me.kafein.common.runnable.DurationRunnable;
import me.kafein.common.tag.TagManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SuperCombatTagPlugin extends JavaPlugin implements SuperCombatTag {

    @Getter
    private static SuperCombatTag instance;

    @Getter
    private ConfigLoader configLoader;
    @Getter
    private TagManager tagManager;

    @Override
    public void onEnable() {

        instance = this;
        SuperCombatTagProvider.setSuperCombatTag(this);

        configLoader = new ConfigLoader()
                .loadConfigs(getDataFolder().getAbsolutePath())
                .loadFields();
        tagManager = new TagManager();

        new ExpansionLoader().load(getDataFolder().getAbsolutePath());

        new BukkitCommandManager(this).registerCommand(new CombatTagCMD(this));

        Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getScheduler().runTaskTimer(this, new DurationRunnable(), 20L, 20L);

    }

    @Override
    public void onDisable() {

    }

}
