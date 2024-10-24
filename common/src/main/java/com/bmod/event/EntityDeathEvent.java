package com.bmod.event;

import com.bmod.registry.item.ModItems;
import com.bmod.registry.mob_effect.ModMobEffects;
import com.bmod.registry.world.ModDimensions;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EntityDeathEvent {

    public static void initialize() {
        EntityEvent.LIVING_DEATH.register((entity, source) -> {
            if (entity.getLevel().isClientSide){
                return EventResult.pass();
            }

            if (entity instanceof ServerPlayer player) {
                if (player.level.dimension() == ModDimensions.BLYDIM_KEY)
                {
                    player.clearFire();
                    player.setAirSupply(player.getMaxAirSupply());

                    player.setHealth(player.getMaxHealth());

                    for (ItemStack item : player.getInventory().items)
                    {
                        if (item.getItem() == ModItems.CURSED_GEM.get() || item.getItem() == ModItems.VOODOO_DOLL.get())
                        {
                            item.setTag(new CompoundTag());
                        }
                    }

                    BlockPos respawnPos = player.getRespawnPosition() != null ? player.getRespawnPosition() : player.server.overworld().getSharedSpawnPos();

                    player.teleportTo(player.level.getServer().getLevel(Level.OVERWORLD),
                            respawnPos.getX(),
                            respawnPos.getY(),
                            respawnPos.getZ(),
                            0,0);

                    return EventResult.interruptFalse();
                }
                else if (player.getOffhandItem().getItem() == ModItems.TOTEM_OF_DREAMS.get() || player.getMainHandItem().getItem() == ModItems.TOTEM_OF_DREAMS.get()) {
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
                else if (player.hasEffect(ModMobEffects.CARDIAC_ARREST.get())) {
                    player.removeEffect(ModMobEffects.CARDIAC_ARREST.get());
                    player.setHealth(player.getMaxHealth());

                    player.teleportTo(player.level.getServer().getLevel(ModDimensions.BLYDIM_KEY),
                            player.position().x,
                            player.position().y,
                            player.position().z,
                            0,0);

                    return EventResult.interruptFalse();
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
            
            
            return EventResult.pass();
        });
    }
}
