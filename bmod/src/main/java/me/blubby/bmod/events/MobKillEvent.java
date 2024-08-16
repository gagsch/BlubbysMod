package me.blubby.bmod.events;

import me.blubby.bmod.content.item.ModItems;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MobKillEvent {

    @SuppressWarnings("resource")
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event.getEntity().getLevel().isClientSide){
            return;
        }

        if (event.getEntity() instanceof Warden warden) {
            warden.spawnAtLocation(ModItems.WARDEN_CORE.get());
        }

        if (event.getEntity() instanceof ElderGuardian guardian) {
            guardian.spawnAtLocation(ModItems.GUARDIAN_CORE.get());
        }
    }
}