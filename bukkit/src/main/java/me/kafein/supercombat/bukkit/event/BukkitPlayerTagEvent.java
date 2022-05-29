package me.kafein.supercombat.bukkit.event;

import lombok.Getter;
import lombok.Setter;
import me.kafein.supercombat.common.tag.Tag;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BukkitPlayerTagEvent extends Event implements Cancellable {

    @Getter private final static HandlerList handlerList = new HandlerList();

    @Getter private final Player player;
    @Getter private final Tag tag;
    @Getter private final boolean reTag;
    @Getter @Setter private boolean cancelled;

    public BukkitPlayerTagEvent(Player player, Tag tag, boolean reTag, boolean cancelled) {
        this.player = player;
        this.tag = tag;
        this.reTag = reTag;
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

}
