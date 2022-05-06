package me.kafein.bukkit;

import co.aikar.commands.BukkitCommandManager;
import lombok.Getter;
import me.kafein.bukkit.command.BukkitCombatCMD;
import me.kafein.bukkit.listener.BukkitDamageListener;
import me.kafein.bukkit.listener.BukkitDeathListener;
import me.kafein.bukkit.listener.BukkitProtectionListener;
import me.kafein.bukkit.listener.BukkitPunishmentListener;
import me.kafein.bukkit.listener.adapter.BukkitListenerAdapter;
import me.kafein.common.SuperCombat;
import me.kafein.common.SuperCombatProvider;
import me.kafein.common.config.ConfigKeys;
import me.kafein.common.config.ConfigManager;
import me.kafein.common.config.ConfigType;
import me.kafein.common.config.language.Language;
import me.kafein.common.expansion.ExpansionManager;
import me.kafein.common.runnable.TagDurationRunnable;
import me.kafein.common.tag.TagManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SuperCombatPlugin extends JavaPlugin implements SuperCombat {

    @Getter
    private static SuperCombatPlugin instance;

    @Getter
    private ConfigManager configManager;
    @Getter
    private TagManager tagManager;
    @Getter
    private ExpansionManager expansionManager;

    @Override
    public void onEnable() {

        instance = this;
        SuperCombatProvider.setSuperCombat(this);

        configManager = new ConfigManager();
        loadConfigs();
        tagManager = new TagManager();
        expansionManager = new ExpansionManager()
                .loadFromDataFolder(getDataFolder().getAbsolutePath());

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

        expansionManager.disableAll();

    }

    public void loadConfigs() {
        configManager.loadConfig("settings", getDataFolder().getAbsolutePath() + "/settings.yml", SuperCombat.class, ConfigKeys.Settings.class, true);
        String language = configManager.getConfig("settings").getNode("settings", "language").getString();
        if (language == null || Language.of(language) == null) language = "en";
        configManager.loadConfig("language", getDataFolder().getAbsolutePath() + "/language/language_" + language + ".yml", SuperCombat.class, ConfigKeys.Language.class, true);
    }

}
