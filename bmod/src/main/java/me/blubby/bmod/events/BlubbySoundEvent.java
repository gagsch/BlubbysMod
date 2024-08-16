package me.blubby.bmod.events;

import me.blubby.bmod.Blubby_sModOfDoom;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlubbySoundEvent {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Blubby_sModOfDoom.MOD_ID);

    public static final RegistryObject<SoundEvent> ITEM_ARMOR_EQUIP_NIGHTMARE = register("item.armor.equip_nightmare");

    private static RegistryObject<SoundEvent> register(String location) {
        return SOUNDS.register(location, () -> new SoundEvent(new ResourceLocation(Blubby_sModOfDoom.MOD_ID, location)));
    }
}