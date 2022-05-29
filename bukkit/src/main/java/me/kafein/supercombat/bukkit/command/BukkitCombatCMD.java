package me.kafein.supercombat.bukkit.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import me.kafein.supercombat.bukkit.SuperCombatPlugin;
import me.kafein.supercombat.bukkit.util.ColorSerializer;
import me.kafein.supercombat.common.config.key.ConfigKeys;
import me.kafein.supercombat.common.expansion.handler.ExpansionHandler;
import me.kafein.supercombat.common.tag.handler.TagHandler;
import org.bukkit.command.CommandSender;

@CommandAlias("supercombattag|supercombat|combattag|sct|sc|ct")
@Description("SuperCombat main command.")
@SuppressWarnings("unused")
public class BukkitCombatCMD extends BaseCommand {

    private final SuperCombatPlugin plugin = SuperCombatPlugin.getInstance();

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
        plugin.loadConfigs();
        sender.sendMessage("§aCombatTag config reloaded.");
        ExpansionHandler.reloadConfigAll();
    }

    @Subcommand("list")
    @Description("Shows the list of tagged players.")
    public void onList(CommandSender sender) {
        if (!sender.hasPermission(ConfigKeys.Settings.ADMIN_PERM.getValue())) {
            sender.sendMessage(ColorSerializer.serialize(ConfigKeys.Language.NO_PERMISSION.getValue()));
            return;
        }
        TagHandler.getTagMap().values().forEach(tag -> {
            sender.sendMessage(tag.getUser().getName() + " : " + tag.getDuration());
        });
    }

}
