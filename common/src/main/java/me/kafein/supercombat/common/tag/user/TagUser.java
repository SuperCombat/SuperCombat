package me.kafein.supercombat.common.tag.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class TagUser {

    private final String name;
    private final UUID uuid;

}
