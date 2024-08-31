package me.blubby.bmod.common.events;

import me.blubby.bmod.Blubby_sModOfDoom;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlubbySoundEvent {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Blubby_sModOfDoom.MOD_ID);

    public static final RegistryObject<SoundEvent> EQUIP_NIGHTMARE = register("equip_nightmare");
    public static final RegistryObject<SoundEvent> ROT_FLY_BUZZ = register("rot_fly_buzz");
    public static final RegistryObject<SoundEvent> ROT_FLY_HURT = register("rot_fly_hurt");

    public static final RegistryObject<SoundEvent> BEHEMOTH_GROWL = register("behemoth_growl");
    public static final RegistryObject<SoundEvent> BEHEMOTH_HURT = register("behemoth_hurt");
    public static final RegistryObject<SoundEvent> BEHEMOTH_DEATH = register("behemoth_death");

    public static final RegistryObject<SoundEvent> SNOW_FLINX_SNIFF = register("snow_flinx_sniff");
    public static final RegistryObject<SoundEvent> SNOW_FLINX_HURT = register("snow_flinx_hurt");
    public static final RegistryObject<SoundEvent> SNOW_FLINX_DEATH = register("snow_flinx_death");


    private static RegistryObject<SoundEvent> register(String location) {
        return SOUNDS.register(location, () -> new SoundEvent(new ResourceLocation(Blubby_sModOfDoom.MOD_ID, location)));
    }
}