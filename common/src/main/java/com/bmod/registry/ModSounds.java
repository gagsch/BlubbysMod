package com.bmod.registry;

import com.bmod.BlubbysMod;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.SOUND_EVENT_REGISTRY);

    public static final Supplier<SoundEvent> EQUIP_MOD_ARMOR_1 = register("item/armor/equip_mod_armor_1");

    public static final Supplier<SoundEvent> ROT_FLY_BUZZ = register("rot_fly_buzz");
    public static final Supplier<SoundEvent> ROT_FLY_HURT = register("rot_fly_hurt");

    public static final Supplier<SoundEvent> BEHEMOTH_GROWL = register("behemoth_growl");
    public static final Supplier<SoundEvent> BEHEMOTH_HURT = register("behemoth_hurt");
    public static final Supplier<SoundEvent> BEHEMOTH_DEATH = register("behemoth_death");

    public static final Supplier<SoundEvent> SNOW_FLINX_AMBIENCE = register("snow_flinx_ambience");
    public static final Supplier<SoundEvent> SNOW_FLINX_HURT = register("snow_flinx_hurt");
    public static final Supplier<SoundEvent> SNOW_FLINX_DEATH = register("snow_flinx_death");

    private static Supplier<SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(BlubbysMod.MOD_ID, name)));
    }
}