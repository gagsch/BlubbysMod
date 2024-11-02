package com.bmod.event;

import com.bmod.registry.item.ModItems;
import com.bmod.util.ContainerUtils;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.item.ItemStack;

public class EntityDeathEvent {

    public static void initialize() {
        EntityEvent.LIVING_DEATH.register((entity, source) -> {
            if (entity.getLevel().isClientSide){
                return EventResult.pass();
            }

            if (entity instanceof ServerPlayer player) {
                if (player.getOffhandItem().getItem() == ModItems.TOTEM_OF_DREAMS.get() || player.getMainHandItem().getItem() == ModItems.TOTEM_OF_DREAMS.get()) {
                    if (player.getOffhandItem().getItem() == ModItems.TOTEM_OF_DREAMS.get()) {
                        player.getOffhandItem().shrink(1);
                    } else {
                        player.getMainHandItem().shrink(1);
                    }

                    player.setHealth(3.0F);

                    ServerLevel respawnLevel = player.server.getLevel(player.getRespawnDimension());
                    BlockPos respawnPos = player.getRespawnPosition() != null ? player.getRespawnPosition() : player.server.overworld().getSharedSpawnPos();

                    if (respawnLevel != null) {
                        if (player.level.dimension() == respawnLevel.dimension()) {
                            player.teleportTo(respawnPos.getX(), respawnPos.getY(), respawnPos.getZ());
                        } else {
                            player.teleportTo(respawnLevel, respawnPos.getX(), respawnPos.getY(), respawnPos.getZ(), 0, 0);
                        }
                    }

                    player.getLevel().broadcastEntityEvent(player, (byte) 35);
                    player.playSound(SoundEvents.TOTEM_USE, 1.0F, 1.0F);

                    player.clearFire();
                    player.setAirSupply(player.getMaxAirSupply());

                    player.inventoryMenu.broadcastChanges();

                    return EventResult.interruptFalse();
                }
                else {
                    SimpleContainer container = new SimpleContainer(5);
                    ContainerUtils.loadContainerFromPlayer(container, player, "accessories");
                    if (!container.hasAnyMatching((itemStack) -> itemStack.is(ModItems.ETERNAL_SATCHEL.get())))
                    {
                        for (int i = 0; i < 5; i++)
                        {
                            ItemEntity itemEntity = new ItemEntity(entity.getLevel(), entity.getX(), entity.getY(), entity.getZ(), container.getItem(i));
                            entity.getLevel().addFreshEntity(itemEntity);
                            container.setItem(i, ItemStack.EMPTY);
                            container.setChanged();
                        }
                    }
                    ContainerUtils.saveContainerToPlayer(container, player, "accessories");
                }
            }
            else if (entity instanceof Warden) {
                if (entity.level.getRandom().nextInt(5) == 1)
                {
                    entity.spawnAtLocation(ModItems.AWAKENED_CORE.get());
                }

                entity.spawnAtLocation(ModItems.WARDEN_TENDRIL.get());
            }
            else if (entity instanceof ElderGuardian) {
                if (entity.level.getRandom().nextInt(10) == 1)
                {
                    entity.spawnAtLocation(ModItems.AWAKENED_CORE.get());
                }

                entity.spawnAtLocation(ModItems.GUARDIAN_CORE.get());
            }
            else if (entity instanceof WitherBoss) {
                if (entity.level.getRandom().nextInt(3) == 1)
                {
                    entity.spawnAtLocation(ModItems.AWAKENED_CORE.get());
                }

                entity.spawnAtLocation(ModItems.WITHER_SPINE.get());
            }
            else if (entity instanceof EnderDragon) {
                if (entity.level.getRandom().nextInt(2) == 1)
                {
                    entity.spawnAtLocation(ModItems.ETERNAL_CORE.get());
                }

                entity.spawnAtLocation(ModItems.DRAGON_HEART.get());
            }
            
            
            return EventResult.pass();
        });
    }
}
