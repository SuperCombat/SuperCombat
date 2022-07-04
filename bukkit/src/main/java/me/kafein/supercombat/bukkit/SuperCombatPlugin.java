package me.kafein.supercombat.bukkit;

import co.aikar.commands.BukkitCommandManager;
import com.google.common.collect.ImmutableList;
import me.kafein.supercombat.bukkit.command.BukkitCombatCMD;
import me.kafein.supercombat.bukkit.listener.*;
import me.kafein.supercombat.bukkit.listener.adapter.ListenerAdapter;
import me.kafein.supercombat.bukkit.listener.protection.BukkitGlideListener;
import me.kafein.supercombat.bukkit.listener.protection.BukkitItemPickupListener;
import me.kafein.supercombat.bukkit.listener.protection.BukkitProtectionListener;
import me.kafein.supercombat.bukkit.util.ProtocolVersion;
import me.kafein.supercombat.common.config.handler.ConfigHandler;
import me.kafein.supercombat.common.expansion.handler.ExpansionHandler;
import me.kafein.supercombat.common.runnable.TagDurationRunnable;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SuperCombatPlugin extends JavaPlugin {

    private ImmutableList<Class<?>> listeners = ImmutableList.of(
            BukkitDamageListener.class,
            BukkitDeathListener.class,
            BukkitPunishmentListener.class,
            BukkitProtectionListener.class,
            BukkitItemPickupListener.getSelectedClass()
    );

    private final ImmutableList<Class<?>> tagListeners = ImmutableList.of(
            BukkitTagListener.class
    );

    @Override
    public void onEnable() {

        ConfigHandler.init();
        ExpansionHandler.init();

        BukkitCommandManager commandManager = new BukkitCommandManager(this);
        commandManager.registerCommand(new BukkitCombatCMD());

        if (ProtocolVersion.isNewerOrEqual(ProtocolVersion.Version.v1_9)) {
            ListenerAdapter.registerBukkitListener(this, BukkitGlideListener.class);
        }
        ListenerAdapter.registerBukkitListener(this, listeners);
        ListenerAdapter.registerTagListener(tagListeners);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new TagDurationRunnable(), 20L, 20L);

    }

    @Override
    public void onDisable() {

        ExpansionHandler.disableAll();

    }

}
