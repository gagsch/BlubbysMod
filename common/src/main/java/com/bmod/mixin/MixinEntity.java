package com.bmod.mixin;

import com.bmod.util.mixinUtils.PersistentDataAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Inject(method = "saveWithoutId", at = @At(value = "TAIL"))
    public void saveWithoutId(CompoundTag arg, CallbackInfoReturnable<CompoundTag> cir) {
        if (PersistentDataAccess.persistentData != null) {
            arg.put("Data", PersistentDataAccess.persistentData.copy());
        }
    }
}
