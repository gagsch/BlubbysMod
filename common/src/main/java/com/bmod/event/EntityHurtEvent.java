package com.bmod.event;

import com.bmod.registry.item.ModItems;
import com.bmod.util.ContainerUtils;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;

public class EntityHurtEvent {

    public static void initialize() {
        EntityEvent.LIVING_HURT.register((entity, source, damage) -> {
            if (source.getEntity() instanceof ServerPlayer player)
            {
                SimpleContainer container = new SimpleContainer(5);
                ContainerUtils.loadContainerFromPlayer(container, player, "accessories");

                if (container.hasAnyMatching((itemStack) -> itemStack.is(ModItems.MYSTIC_EMBER.get())))
                {
                    entity.setSecondsOnFire(5);
                }
                if (container.hasAnyMatching((itemStack) -> itemStack.is(ModItems.VAMPIRE_GLOVES.get())))
                {
                    player.heal(damage / 15);
                }
            }
            return EventResult.pass();
        });
    }
}
