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
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

@CommandAlias("supercombattag|combattag|sct|ct")
@Description("SuperCombatTag main command.")
@RequiredArgsConstructor
public class CombatTagCMD extends BaseCommand {

    private final ConfigLoader configLoader = SuperCombatTagPlugin.getInstance().getConfigLoader();

    private final Plugin plugin;

    @HelpCommand
    @Description("Shows the help message.")
    public void onHelp(CommandSender sender) {
        ConfigKeys.HELP_MESSAGE.getValue().forEach(message -> {
            sender.sendMessage(ColorSerializer.serialize(message));
        });
    }

    @Subcommand("reload")
    @Description("Reloads the plugin's configs.")
    public void onReload(CommandSender sender) {
        if (!sender.hasPermission(ConfigKeys.ADMIN_PERM.getValue())) {
            sender.sendMessage(ColorSerializer.serialize(ConfigKeys.NO_PERMISSION.getValue()));
            return;
        }
        configLoader
                .loadConfigs(plugin.getDataFolder().getAbsolutePath())
                .loadFields();
        sender.sendMessage("§aCombatTag config reloaded.");
    }

}
