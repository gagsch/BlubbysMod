package com.bmod.event;

import com.bmod.registry.item.ModItems;
import com.bmod.util.ContainerUtils;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;

public class EntityDeathEvent {

    public static void initialize() {
        EntityEvent.LIVING_DEATH.register((entity, source) -> {
            if (entity.getLevel().isClientSide){
                return EventResult.pass();
            }

            RandomSource random = entity.getLevel().getRandom();

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
                else if (!player.getLevel().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) {
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
                    ContainerUtils.saveAccessoriesToPlayer(container, player);
                }
            }
            else if (entity instanceof Warden) {
                entity.spawnAtLocation(ModItems.WARDEN_TENDRIL.get());
            }
            else if (entity instanceof ElderGuardian) {
                entity.spawnAtLocation(ModItems.GUARDIAN_CORE.get());
            }
            else if (entity instanceof WitherBoss) {
                entity.spawnAtLocation(ModItems.WITHER_SPINE.get());
            }
            else if (entity instanceof EnderDragon) {
                entity.spawnAtLocation(ModItems.DRAGON_HEART.get());
            }
            else if (entity instanceof Blaze) {
                if (random.nextInt(0, 75) == 0) {
                    entity.spawnAtLocation(ModItems.MOLTEN_SLAG.get());
                }
            }
            else if (entity instanceof Shulker) {
                if (random.nextInt(0, 120) == 0) {
                    entity.spawnAtLocation(ModItems.GRAVITY_BOOTS.get());
                }
            }

            return EventResult.pass();
        });
    }
}
