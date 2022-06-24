package me.kafein.supercombat.bukkit;

import co.aikar.commands.BukkitCommandManager;
import lombok.Getter;
import me.kafein.supercombat.bukkit.command.BukkitCombatCMD;
import me.kafein.supercombat.bukkit.listener.BukkitDamageListener;
import me.kafein.supercombat.bukkit.listener.BukkitDeathListener;
import me.kafein.supercombat.bukkit.listener.BukkitProtectionListener;
import me.kafein.supercombat.bukkit.listener.BukkitPunishmentListener;
import me.kafein.supercombat.bukkit.listener.adapter.BukkitListenerAdapter;
import me.kafein.supercombat.common.SuperCombat;
import me.kafein.supercombat.common.config.ConfigType;
import me.kafein.supercombat.common.config.handler.ConfigHandler;
import me.kafein.supercombat.common.config.key.ConfigKeys;
import me.kafein.supercombat.common.expansion.handler.ExpansionHandler;
import me.kafein.supercombat.common.runnable.TagDurationRunnable;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SuperCombatPlugin extends JavaPlugin {

    @Getter
    private static SuperCombatPlugin instance;

    @Override
    public void onEnable() {

        instance = this;

        loadConfigs();
        ExpansionHandler.init();

        new BukkitCommandManager(this).registerCommand(new BukkitCombatCMD());

        BukkitListenerAdapter.register(this,
                BukkitDamageListener.class,
                BukkitDeathListener.class,
                BukkitPunishmentListener.class,
                BukkitProtectionListener.class);

        Bukkit.getScheduler().runTaskTimer(this, new TagDurationRunnable(), 20L, 20L);

    }

    @Override
    public void onDisable() {


    }

    public void loadConfigs() {
        ClassLoader classLoader = SuperCombat.class.getClassLoader();
        ConfigHandler.createConfig("settings",
                "settings.yml",
                classLoader.getResourceAsStream("settings.yml")).ifPresent(config -> {
            ConfigHandler.put(ConfigType.SETTINGS, config);
            ConfigHandler.putNodesToClass(config, ConfigType.SETTINGS.getClazz(), true);
        });
        String language = ConfigKeys.Settings.LANGUAGE.getValue();
        ConfigHandler.createConfig("language",
                "language/language_" + language + ".yml",
                classLoader.getResourceAsStream("language/language_" + language + ".yml")).ifPresent(config -> {
            ConfigHandler.put(ConfigType.LANGUAGE, config);
            ConfigHandler.putNodesToClass(config, ConfigType.LANGUAGE.getClazz(), true);
        });
    }

}
