package me.kafein.supercombat.bukkit.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.kafein.supercombat.bukkit.util.language.LanguageUtil;
import me.kafein.supercombat.common.blocker.Blockers;
import me.kafein.supercombat.common.config.handler.ConfigHandler;
import me.kafein.supercombat.common.config.key.ConfigKeys;
import me.kafein.supercombat.common.expansion.handler.ExpansionHandler;
import me.kafein.supercombat.common.tag.handler.TagHandler;
import me.kafein.supercombat.common.tag.untag.UntagReason;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

@CommandAlias("supercombat")
@Description("SuperCombat main command.")
@SuppressWarnings("unused")
public class BukkitCombatCMD extends BaseCommand {

    @HelpCommand
    @Description("Shows the help message.")
    public void helpCommand(CommandSender sender) {
        if (!hasPermission(sender)) return;
        LanguageUtil.replaceList(ConfigKeys.Language.HELP_MESSAGE.getValue())
                .forEach(sender::sendMessage);
    }

    @Subcommand("reload")
    @Description("Reloads the plugin's configs.")
    public void reloadCommand(CommandSender sender) {
        if (!hasPermission(sender)) return;
        ConfigHandler.init();
        Blockers.init();
        ExpansionHandler.reloadConfigAll();
        sender.sendMessage(LanguageUtil.replace(ConfigKeys.Language.RELOADED.getValue()));
    }

    @Subcommand("untag")
    @CommandCompletion("@players")
    @Description("Untag the player.")
    public void untagCommand(CommandSender sender, String[] args) {
        if (!hasPermission(sender)) return;
        if (args.length < 1) {
            sender.sendMessage("§6/supercombat untag <player>");
            return;
        }
        String playerName = args[0];
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            sender.sendMessage(LanguageUtil.replace(ConfigKeys.Language.PLAYER_NOT_FOUND.getValue(),
                    new String[]{"%player%"},
                    new String[]{playerName}
            ));
            return;
        }
        TagHandler.unTagPlayer(player.getUniqueId(), UntagReason.UNKNOWN);
        player.sendMessage("§6" + player.getName() + " &eis untagged.");
    }

    private boolean hasPermission(CommandSender sender) {
        if (!sender.hasPermission(ConfigKeys.Settings.ADMIN_PERM.getValue())) {
            Plugin plugin = Bukkit.getPluginManager().getPlugin("SuperCombat");
            sender.sendMessage("");
            sender.sendMessage("  §6§l❘ §e§lSuperCombat");
            sender.sendMessage("");
            sender.sendMessage("  §e§l▪ §7Author§8: §eKafein");
            sender.sendMessage("  §e§l▪ §7Version§8: §e" + plugin.getDescription().getVersion());
            sender.sendMessage("  §e§l▪ §7Discord§8: §ediscord.gg/vx95fKG7fw");
            sender.sendMessage("");
            sender.sendMessage(LanguageUtil.replace(ConfigKeys.Language.NO_PERMISSION.getValue()));
            return false;
        }
        return true;
    }

}