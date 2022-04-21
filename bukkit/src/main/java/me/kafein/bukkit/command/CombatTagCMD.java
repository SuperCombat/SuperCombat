package me.kafein.bukkit.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import lombok.RequiredArgsConstructor;
import me.kafein.bukkit.SuperCombatTagPlugin;
import me.kafein.bukkit.util.ColorSerializer;
import me.kafein.common.config.ConfigKeys;
import me.kafein.common.config.ConfigLoader;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

@CommandAlias("supercombattag|combattag|sct|ct")
@Description("SuperCombatTag plugin's main command.")
@RequiredArgsConstructor
public class CombatTagCMD extends BaseCommand {

    private final ConfigLoader configLoader = SuperCombatTagPlugin.getInstance().getConfigLoader();

    private final Plugin plugin;

    @HelpCommand
    @Description("Shows this help message.")
    public void onHelp(Player player) {

    }

    @Subcommand("reload")
    @Description("Reloads the plugin's configuration.")
    public void onReload(Player player) {
        if (!player.hasPermission(ConfigKeys.ADMIN_PERM.getValue())) {
            player.sendMessage(ColorSerializer.serialize(ConfigKeys.NO_PERMISSION.getValue()));
            return;
        }
        configLoader
                .loadConfigs(getClass(), plugin.getDataFolder().getAbsolutePath())
                .loadFields();
        player.sendMessage("§aCombatTag config reloaded.");
    }

}
