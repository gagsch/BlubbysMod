package com.bmod.mixin;

import com.bmod.BlubbysMod;
import com.bmod.util.mixinUtils.IEntityDataSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(Entity.class)
public abstract class MixinEntity implements IEntityDataSaver {
    @Shadow public abstract UUID getUUID();

    @Unique
    private CompoundTag blubbysmod$persistentData;

    @Override
    public CompoundTag blubbysmod$getPersistentData() {
        if(this.blubbysmod$persistentData == null) {
            CompoundTag compoundTag = IEntityDataSaver.blubbysmod$uuidCompoundTag.get(this.getUUID());
            this.blubbysmod$persistentData = compoundTag == null ? new CompoundTag() : compoundTag;
        }
        IEntityDataSaver.blubbysmod$uuidCompoundTag.put(this.getUUID(), blubbysmod$persistentData);
        return blubbysmod$persistentData;
    }

    @Inject(method = "saveWithoutId", at = @At("HEAD"))
    protected void saveWithoutID(CompoundTag compoundTag, CallbackInfoReturnable<CompoundTag> cir) {
        if(blubbysmod$persistentData != null) {
            compoundTag.put(BlubbysMod.MOD_ID + "data", blubbysmod$persistentData);
        }
    }

    @Inject(method = "load", at = @At("HEAD"))
    protected void load(CompoundTag compoundTag, CallbackInfo info) {
        if (compoundTag.contains(BlubbysMod.MOD_ID + "data", 10)) {
            blubbysmod$persistentData = compoundTag.getCompound(BlubbysMod.MOD_ID + "data");
        }
    }
}