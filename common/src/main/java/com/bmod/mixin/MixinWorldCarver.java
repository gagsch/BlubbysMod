package com.bmod.mixin;

import com.bmod.registry.block.ModBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldCarver.class)
public class MixinWorldCarver<C extends CarverConfiguration> {

    @Inject(method = "canReplaceBlock", at = @At("RETURN"), cancellable = true)
    protected void canReplaceBlock(C carverConfiguration, BlockState blockState, CallbackInfoReturnable<Boolean> cir) {
        boolean modBlocks =
                blockState.is(ModBlocks.DARK_SOIL.get()) ||
                blockState.is(ModBlocks.DARK_TURF_BLOCK.get()) ||
                blockState.is(ModBlocks.NECROTIC_GRASS_BLOCK.get()) ||
                blockState.is(ModBlocks.DEEPERSLATE.get()) ||
                blockState.is(ModBlocks.WEB_STONE.get()) ||
                blockState.is(ModBlocks.SILK_BLOCK.get());

        cir.setReturnValue(blockState.is(carverConfiguration.replaceable) || modBlocks);
    }
}
