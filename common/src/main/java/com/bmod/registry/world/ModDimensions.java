package com.bmod.registry.world;

import com.bmod.BlubbysMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions {
    public static final ResourceKey<Level> BLYDIM_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(BlubbysMod.MOD_ID, "nightmare_realm"));
    public static final ResourceKey<DimensionType> BLYDIM_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, BLYDIM_KEY.location());
}
