package me.blubby.bmod.common.events;

import me.blubby.bmod.common.item.ModItems;
import me.blubby.bmod.server.world.portal.ModTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntityDeathEvent {
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event.getEntity().getLevel().isClientSide){
            return;
        }


        if (event.getEntity() instanceof ServerPlayer player) {
            if (player.getOffhandItem().getItem() == ModItems.TOTEM_OF_DREAMS.get() || player.getMainHandItem().getItem() == ModItems.TOTEM_OF_DREAMS.get()) {
                event.setCanceled(true);

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
                        player.changeDimension(respawnLevel, new ModTeleporter(respawnPos));
                    }
                }

                player.getLevel().broadcastEntityEvent(player, (byte) 35);
                player.playSound(SoundEvents.TOTEM_USE, 1.0F, 1.0F);

                player.clearFire();
                player.setAirSupply(player.getMaxAirSupply());

                player.inventoryMenu.broadcastChanges();
            }
        }
        else if (event.getEntity() instanceof Warden) {
            event.getEntity().spawnAtLocation(ModItems.WARDEN_CORE.get());
        }
        else if (event.getEntity() instanceof ElderGuardian) {
            event.getEntity().spawnAtLocation(ModItems.GUARDIAN_CORE.get());
        }
    }
}