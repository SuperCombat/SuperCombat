package me.kafein.bukkit.event;

import lombok.Getter;
import lombok.Setter;
import me.kafein.common.tag.Tag;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BukkitPlayerTagEvent extends Event implements Cancellable {

    @Getter private final static HandlerList handlerList = new HandlerList();

    @Getter private final Player player;
    @Getter private final Tag tag;
    @Getter @Setter private boolean cancelled;

    public BukkitPlayerTagEvent(Player player, Tag tag, boolean cancelled) {
        this.player = player;
        this.tag = tag;
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

}
