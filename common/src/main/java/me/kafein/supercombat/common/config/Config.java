package me.kafein.supercombat.common.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ninja.leaping.configurate.ConfigurationNode;

@RequiredArgsConstructor
@Getter
public class Config {

    private final String name;
    private final String path;
    private final ConfigurationNode node;

}
