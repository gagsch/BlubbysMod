package com.bmod.registry.item.custom;

import com.bmod.registry.item.ModCreativeTab;
import com.bmod.registry.item.ModItems;
import com.bmod.registry.item.tier.ModItemTier;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;

public class ImmersionBreakerItem extends SwordItem {

    public ImmersionBreakerItem() {
        super(ModItemTier.UNIQUE, 29, 0f, new Properties().tab(ModCreativeTab.BLUBBYS_MOD).durability(-1).fireResistant());
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        int newAttackAmount = itemStack.getOrCreateTag().getInt("attacks") + 1;

        attacker.level.playSound(null, attacker.blockPosition(), SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, (float) newAttackAmount / 4, 0.8f);
        if (attacker instanceof ServerPlayer player) {
            if (player.getInventory().countItem(ModItems.IMMERSION_BREAKER.get()) > 1) {
                newAttackAmount = 0;
                player.connection.disconnect(Component.literal("You cannot cheat the Immersion Breaker; there is only room for one of us."));
            }
            if (newAttackAmount == 6) {
                newAttackAmount = 0;
                player.connection.disconnect(Component.literal("You are unworthy of the Immersion Breaker. Reflect on your impatience."));
            }
        }

        itemStack.getOrCreateTag().putInt("attacks", newAttackAmount);

        return super.hurtEnemy(itemStack, target, attacker);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean bl) {
        int attacks = itemStack.getOrCreateTag().getInt("attacks");

        if (attacks > 0 && entity.tickCount % 160 == 0) {
            attacks--;
        }

        itemStack.getOrCreateTag().putInt("attacks", attacks);
    }
}
