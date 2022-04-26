package me.kafein.bukkit;

import co.aikar.commands.BukkitCommandManager;
import lombok.Getter;
import me.kafein.bukkit.command.BukkitCombatCMD;
import me.kafein.bukkit.listener.BukkitDamageListener;
import me.kafein.bukkit.listener.BukkitDeathListener;
import me.kafein.bukkit.listener.BukkitPlayerListener;
import me.kafein.bukkit.listener.adapter.BukkitListenerAdapter;
import me.kafein.common.SuperCombat;
import me.kafein.common.SuperCombatProvider;
import me.kafein.common.config.ConfigLoader;
import me.kafein.common.expansion.ExpansionLoader;
import me.kafein.common.runnable.TagDurationRunnable;
import me.kafein.common.tag.TagManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SuperCombatPlugin extends JavaPlugin implements SuperCombat {

    @Getter
    private static SuperCombatPlugin instance;

    @Getter
    private ConfigLoader configLoader;
    @Getter
    private TagManager tagManager;

    @Override
    public void onEnable() {

        instance = this;
        SuperCombatProvider.setSuperCombat(this);

        configLoader = new ConfigLoader()
                .loadConfigs(getDataFolder().getAbsolutePath())
                .loadFields();
        tagManager = new TagManager();

        new ExpansionLoader().load(getDataFolder().getAbsolutePath());

        new BukkitCommandManager(this).registerCommand(new BukkitCombatCMD(this));

        BukkitListenerAdapter.register(this, BukkitDamageListener.class, BukkitDeathListener.class, BukkitPlayerListener.class);

        Bukkit.getScheduler().runTaskTimer(this, new TagDurationRunnable(), 20L, 20L);

    }

    @Override
    public void onDisable() {

    }

}
