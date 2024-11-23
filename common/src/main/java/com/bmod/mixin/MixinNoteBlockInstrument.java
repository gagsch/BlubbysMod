package com.bmod.mixin;

import com.bmod.registry.block.ModBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoteBlockInstrument.class)
public class MixinNoteBlockInstrument {

    @Inject(method = "byState", at = @At("HEAD"), cancellable = true)
    private static void byState(BlockState blockState, CallbackInfoReturnable<NoteBlockInstrument> cir)
    {
        if (blockState.is(ModBlocks.RUBY_BLOCK.get()))
        {
            cir.setReturnValue(NoteBlockInstrument.BIT);
        }

        if (blockState.is(ModBlocks.VOLCANIC_BLOCK.get()))
        {
            cir.setReturnValue(NoteBlockInstrument.BIT);
        }

        if (blockState.is(ModBlocks.DREADIUM_BLOCK.get()))
        {
            cir.setReturnValue(NoteBlockInstrument.IRON_XYLOPHONE);
        }

        if (blockState.is(ModBlocks.DIVINE_BLOCK.get()))
        {
            cir.setReturnValue(NoteBlockInstrument.BELL);
        }

        if (blockState.is(ModBlocks.SHROOMITE_BLOCK.get()))
        {
            cir.setReturnValue(NoteBlockInstrument.GUITAR);
        }

        if (blockState.is(ModBlocks.FOSSILIZED_BONE_BLOCK.get()))
        {
            cir.setReturnValue(NoteBlockInstrument.XYLOPHONE);
        }

        if (blockState.is(ModBlocks.BUBBLE_BLOCK.get()))
        {
            cir.setReturnValue(NoteBlockInstrument.FLUTE);
        }
    }

}
