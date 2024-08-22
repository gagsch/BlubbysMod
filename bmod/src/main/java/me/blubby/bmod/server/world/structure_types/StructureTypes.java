package me.blubby.bmod.server.world.structure_types;

import me.blubby.bmod.Blubby_sModOfDoom;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class StructureTypes {
    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, Blubby_sModOfDoom.MOD_ID);

    public static final RegistryObject<StructureType<FloatingLandStructure>> FLOATING_LAND_STRUCTURE = STRUCTURES.register(
            "floating_land",
            () -> () -> FloatingLandStructure.CODEC
    );

    public static void register(IEventBus eventBus) {
        STRUCTURES.register(eventBus);
    }
}
