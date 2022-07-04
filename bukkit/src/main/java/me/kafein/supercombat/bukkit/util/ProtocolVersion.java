package me.kafein.supercombat.bukkit.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;

import java.util.Objects;

public class ProtocolVersion {

    public static final Version CURRENT_VERSION;

    static {
        String bukkitVersion = Bukkit.getServer().getClass().getName().split("\\.")[3];
        CURRENT_VERSION = Version.find(bukkitVersion);
    }

    public static boolean isNewer(Version version) {
        Objects.requireNonNull(version, "CURRENT_VERSION cannot be null!");
        return ProtocolVersion.CURRENT_VERSION.ordinal() > version.ordinal();
    }

    public static boolean isNewerOrEqual(Version version) {
        Objects.requireNonNull(version, "CURRENT_VERSION cannot be null!");
        return ProtocolVersion.CURRENT_VERSION.ordinal() >= version.ordinal();
    }

    public static boolean isOlder(Version version) {
        Objects.requireNonNull(version, "CURRENT_VERSION cannot be null!");
        return ProtocolVersion.CURRENT_VERSION.ordinal() < version.ordinal();
    }

    public static boolean isOlderOrEqual(Version version) {
        Objects.requireNonNull(version, "CURRENT_VERSION cannot be null!");
        return ProtocolVersion.CURRENT_VERSION.ordinal() <= version.ordinal();
    }

    @RequiredArgsConstructor
    @Getter
    public enum Version {
        v1_8("v1_8_R3"),
        v1_9("v1_9_R2"),
        v1_10("v1_10_R1"),
        v1_11("v1_11_R1"),
        v1_12("v1_12_R1"),
        v1_13("v1_13_R2"),
        v1_14("v1_14_R1"),
        v1_15("v1_15_R1"),
        v1_16("v1_16_R3"),
        v1_17("v1_17_R1"),
        v1_18("v1_18_R2");

        private final String nmsVersion;

        public static Version find(String version) {
            for (Version v : values()) {
                if (version.startsWith(v.name())) return v;
            }
            return null;
        }

    }

}
