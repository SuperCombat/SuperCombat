package me.kafein.supercombat.common.blocker;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.kafein.supercombat.common.blocker.type.*;
import me.kafein.supercombat.common.blocker.type.block.BlockBreakBlocker;
import me.kafein.supercombat.common.blocker.type.block.BlockPlaceBlocker;
import me.kafein.supercombat.common.blocker.type.item.ItemDropBlocker;
import me.kafein.supercombat.common.blocker.type.item.ItemPickupBlocker;

@RequiredArgsConstructor
@Getter
public enum Blockers {

    WORLD_BLOCKER(new WorldBlocker()),
    COMMAND_BLOCKER(new CommandBlocker()),
    FLY_BLOCKER(new FlyBlocker()),
    INVENTORY_BLOCKER(new InventoryBlocker()),
    TELEPORT_BLOCKER(new TeleportBlocker()),
    BLOCK_BREAK_BLOCKER(new BlockBreakBlocker()),
    BLOCK_PLACE_BLOCKER(new BlockPlaceBlocker()),
    ITEM_DROP_BLOCKER(new ItemDropBlocker()),
    ITEM_PICKUP_BLOCKER(new ItemPickupBlocker()),
    ELYTRA_BLOCKER(new ElytraBlocker()),
    CHORUSFRUIT_BLOCKER(new ChorusFruitBlocker());

    private final Blocker blocker;

    static {
        for (Blockers blocker : values()) {
            blocker.getBlocker().init();
        }
    }

    public static void init() {
        for (Blockers type : values()) {
            type.getBlocker().init();
        }
    }

}
