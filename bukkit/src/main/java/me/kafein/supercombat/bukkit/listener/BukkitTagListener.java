package me.kafein.supercombat.bukkit.listener;

import me.kafein.supercombat.bukkit.util.language.LanguageUtil;
import me.kafein.supercombat.common.config.key.ConfigKeys;
import me.kafein.supercombat.common.event.EventType;
import me.kafein.supercombat.common.event.impl.RetagEvent;
import me.kafein.supercombat.common.event.impl.TagEvent;
import me.kafein.supercombat.common.event.impl.UntagEvent;
import me.kafein.supercombat.common.listener.Listener;
import me.kafein.supercombat.common.tag.Tag;
import me.kafein.supercombat.common.tag.TagCause;
import me.kafein.supercombat.common.tag.user.TagUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import java.util.List;
import java.util.UUID;

public class BukkitTagListener extends Listener {

    @Override
    public void onTag(TagEvent event) {
        Tag tag = event.getTag();
        TagUser tagUser = tag.getUser();

        executeCommands(EventType.TAG, tag);

        UUID playerUUID = tagUser.getUuid();
        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) return;

        if (ConfigKeys.Settings.INVENTORY_BLOCKER_ENABLED.getValue()) {
            InventoryType inventoryType = player.getOpenInventory().getType();
            if (inventoryType != InventoryType.CRAFTING) {
                player.closeInventory();
            }
        }

        TagCause tagCause = tag.getCause();
        String durationString = String.valueOf(ConfigKeys.Settings.TAG_DURATION.getValue());
        if (tagCause == TagCause.UNKNOWN) {
            player.sendMessage(LanguageUtil.replace(ConfigKeys.Language.UNKNOWN_CAUSE_TAGGED.getValue(),
                    new String[]{"%duration%"},
                    new String[]{durationString}));
        } else {
            String otherUserName = tag.getOtherUser().getName();
            switch (tag.getReason()){
                case DEFENDER:
                    player.sendMessage(LanguageUtil.replace(ConfigKeys.Language.DEFENDER_TAGGED.getValue(),
                            new String[]{"%duration%", "%attacker%"},
                            new String[]{durationString, otherUserName}));
                    break;
                case ATTACKER:
                    player.sendMessage(LanguageUtil.replace(ConfigKeys.Language.ATTACKER_TAGGED.getValue(),
                            new String[]{"%duration%", "%defender%"},
                            new String[]{durationString, otherUserName}));
                    break;
            }
        }

    }

    @Override
    public void onRetag(RetagEvent event) {
        Tag tag = event.getNewTag();
        executeCommands(EventType.RETAG, tag);
    }

    @Override
    public void onUntag(UntagEvent event) {
        Tag tag = event.getTag();
        executeCommands(EventType.UNTAG, tag);

        UUID playerUUID = tag.getUser().getUuid();
        Player player = Bukkit.getPlayer(playerUUID);
        if (player != null) {
            player.sendMessage(LanguageUtil.replace(ConfigKeys.Language.UNTAGGED.getValue()));
        }
    }

    private void executeCommands(EventType eventType, Tag tag) {
        List<String> commands = null;
        switch (eventType) {
            case TAG:
                if (ConfigKeys.Settings.TAGGING_COMMANDS_ENABLED.getValue()) {
                    commands = ConfigKeys.Settings.TAGGING_COMMANDS.getValue();
                }
                break;
            case RETAG:
                if (ConfigKeys.Settings.RETAGGING_COMMANDS_ENABLED.getValue()) {
                    commands = ConfigKeys.Settings.RETAGGING_COMMANDS.getValue();
                }
                break;
            case UNTAG:
                if (ConfigKeys.Settings.UNTAGGING_COMMANDS_ENABLED.getValue()) {
                    commands = ConfigKeys.Settings.UNTAGGING_COMMANDS.getValue();
                }
                break;
        }
        if (commands != null) {
            String playerName = tag.getUser().getName();
            commands.forEach(commandString -> {
                commandString = commandString.replace("%player%", playerName);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandString);
            });
        }
    }


}
