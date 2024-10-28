package com.bmod.mixin;

import com.bmod.registry.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

@Mixin(WorldCarver.class)
public class MixinWorldCarver<C extends CarverConfiguration> {

    @Inject(method = "carveBlock", at = @At("HEAD"))
    protected void carveBlock(CarvingContext carvingContext, C carverConfiguration, ChunkAccess chunkAccess, Function<BlockPos, Holder<Biome>> function, CarvingMask carvingMask, BlockPos.MutableBlockPos mutableBlockPos, BlockPos.MutableBlockPos mutableBlockPos2, Aquifer aquifer, MutableBoolean mutableBoolean, CallbackInfoReturnable<Boolean> cir) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        BlockState blockState = chunkAccess.getBlockState(mutableBlockPos);
        BlockState blockStateBelow = chunkAccess.getBlockState(mutableBlockPos.below());
        if (blockStateBelow.is(ModBlocks.DARK_SOIL.get())) {
            if (blockState.is(ModBlocks.DARK_TURF_BLOCK.get()) || blockState.is(ModBlocks.NECROTIC_GRASS_BLOCK.get())) {
                chunkAccess.setBlockState(mutableBlockPos.below(), blockState, true);
            }
        }

    }

    @Inject(method = "canReplaceBlock", at = @At("RETURN"), cancellable = true)
    protected void canReplaceBlock(C carverConfiguration, BlockState blockState, CallbackInfoReturnable<Boolean> cir) {
        boolean modBlocks =
                blockState.is(ModBlocks.DARK_SOIL.get()) ||
                blockState.is(ModBlocks.DARK_TURF_BLOCK.get()) ||
                blockState.is(ModBlocks.NECROTIC_GRASS_BLOCK.get()) ||
                blockState.is(ModBlocks.DEEPERSLATE.get()) ||
                blockState.is(ModBlocks.MYCELIUM_DEEPERSLATE.get()) ||
                blockState.is(ModBlocks.WEB_STONE.get()) ||
                blockState.is(ModBlocks.SILK_BLOCK.get());

        cir.setReturnValue(blockState.is(carverConfiguration.replaceable) || modBlocks);
    }
}
