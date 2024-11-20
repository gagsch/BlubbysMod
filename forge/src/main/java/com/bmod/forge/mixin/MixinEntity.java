package com.bmod.forge.mixin;

import com.bmod.BlubbysMod;
import com.bmod.util.mixin_util.IEntityDataSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class MixinEntity implements IEntityDataSaver {
    @Unique
    private CompoundTag blubbysmodofdoom$persistentData;

    @Override
    public CompoundTag blubbysmodofdoom$getPersistentData() {
        if(this.blubbysmodofdoom$persistentData == null) {
            this.blubbysmodofdoom$persistentData = new CompoundTag();
        }
        return blubbysmodofdoom$persistentData;
    }

    @Inject(method = "saveWithoutId", at = @At("HEAD"))
    protected void saveWithoutID(CompoundTag compoundTag, CallbackInfoReturnable<CompoundTag> cir) {
        if(blubbysmodofdoom$persistentData != null) {
            compoundTag.put(BlubbysMod.MOD_ID + "data", blubbysmodofdoom$persistentData);
        }
    }

    @Inject(method = "load", at = @At("HEAD"))
    protected void load(CompoundTag compoundTag, CallbackInfo info) {
        if (compoundTag.contains(BlubbysMod.MOD_ID + "data", 10)) {
            blubbysmodofdoom$persistentData = compoundTag.getCompound(BlubbysMod.MOD_ID + "data");
        }
    }
}