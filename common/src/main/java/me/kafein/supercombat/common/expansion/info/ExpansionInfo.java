package me.kafein.supercombat.common.expansion.info;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ExpansionInfo {

    private final String name;
    private final String description;
    private final String version;
    private final String main;
    private final String author;

}
