package com.bmod.registry.mob_effect.custom;

import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class MycosisEffect extends MobEffect {

    protected MycosisEffect() {
        super(MobEffectCategory.HARMFUL, 0x551212);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        Level level = livingEntity.level;
        BlockPos blockPos = livingEntity.blockPosition();

        livingEntity.hurt(DamageSource.GENERIC, (amplifier + 1));

        if (level.getBlockState(blockPos.below()).is(ModBlocks.DEEPERSLATE.get()))
        {
            if (level instanceof ServerLevel) {
                level.setBlockAndUpdate(blockPos.below(), ModBlocks.MYCELIUM_DEEPERSLATE.get().defaultBlockState());
            }
            else {
                double offsetX = (level.random.nextDouble() - 0.5) * 2;
                double offsetY = (level.random.nextDouble() - 0.5) * 2;
                double offsetZ = (level.random.nextDouble() - 0.5) * 2;

                level.addParticle(
                        ModParticles.SPORE_PARTICLE.get(),
                        livingEntity.position().x + offsetX,
                        livingEntity.position().y + offsetY,
                        livingEntity.position().z + offsetZ,
                        0, 0, 0
                );
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int currentTick, int frequency) {
        return true;
    }
}
