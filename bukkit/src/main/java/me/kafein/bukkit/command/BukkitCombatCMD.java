package me.kafein.bukkit.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import lombok.RequiredArgsConstructor;
import me.kafein.bukkit.SuperCombatPlugin;
import me.kafein.bukkit.util.ColorSerializer;
import me.kafein.common.config.ConfigKeys;
import me.kafein.common.config.ConfigLoader;
import me.kafein.common.tag.TagManager;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

@CommandAlias("supercombattag|supercombat|combattag|sct|sc|ct")
@Description("SuperCombat main command.")
@RequiredArgsConstructor
public class BukkitCombatCMD extends BaseCommand {

    private final ConfigLoader configLoader = SuperCombatPlugin.getInstance().getConfigLoader();
    private final TagManager tagManager = SuperCombatPlugin.getInstance().getTagManager();

    private final Plugin plugin;

    @HelpCommand
    @Description("Shows the help message.")
    public void onHelp(CommandSender sender) {
        ConfigKeys.Language.HELP_MESSAGE.getValue().forEach(message -> {
            sender.sendMessage(ColorSerializer.serialize(message));
        });
    }

    @Subcommand("reload")
    @Description("Reloads the plugin's configs.")
    public void onReload(CommandSender sender) {
        if (!sender.hasPermission(ConfigKeys.Settings.ADMIN_PERM.getValue())) {
            sender.sendMessage(ColorSerializer.serialize(ConfigKeys.Language.NO_PERMISSION.getValue()));
            return;
        }
        configLoader
                .loadConfigs(plugin.getDataFolder().getAbsolutePath())
                .loadFields();
        sender.sendMessage("§aCombatTag config reloaded.");
    }

    @Subcommand("list")
    @Description("Shows the list of tagged players.")
    public void onList(CommandSender sender) {
        if (!sender.hasPermission(ConfigKeys.Settings.ADMIN_PERM.getValue())) {
            sender.sendMessage(ColorSerializer.serialize(ConfigKeys.Language.NO_PERMISSION.getValue()));
            return;
        }
        tagManager.getTagMap().values().forEach(tag -> {
            sender.sendMessage(tag.getUserName() + " : " + tag.getDuration());
        });
    }

}
