package me.kafein.supercombat.bukkit.util.language;

import me.kafein.supercombat.bukkit.util.ColorSerializer;
import me.kafein.supercombat.common.config.key.ConfigKeys;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class LanguageUtil {

    public static String replace(String message) {
        message = message.replace("%prefix%", ConfigKeys.Language.PREFIX.getValue());
        return ColorSerializer.serialize(message);
    }

    public static String replace(String message, String[] targets, String[] replacements) {
        message = message.replace("%prefix%", ConfigKeys.Language.PREFIX.getValue());
        return ColorSerializer.serialize(StringUtils.replaceEach(message, targets, replacements));
    }

    public static List<String> replaceList(List<String> messages) {
        messages.replaceAll(LanguageUtil::replace);
        return messages;
    }

    public static List<String> replaceList(List<String> messages, String[] targets, String[] replacements) {
        messages.replaceAll(message -> replace(message, targets, replacements));
        return messages;
    }

}
