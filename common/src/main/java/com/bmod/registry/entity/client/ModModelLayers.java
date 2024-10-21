package com.bmod.registry.entity.client;

import com.bmod.BlubbysMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation ROT_FLY_LAYER = new ModelLayerLocation(
            new ResourceLocation(BlubbysMod.MOD_ID, "rot_fly_layer"), "main");
    public static final ModelLayerLocation BEHEMOTH_LAYER = new ModelLayerLocation(
            new ResourceLocation(BlubbysMod.MOD_ID, "behemoth_layer"), "main");
    public static final ModelLayerLocation SNOW_FLINX_LAYER = new ModelLayerLocation(
            new ResourceLocation(BlubbysMod.MOD_ID, "snow_flinx_layer"), "main");
    public static final ModelLayerLocation LEECH_LAYER = new ModelLayerLocation(
            new ResourceLocation(BlubbysMod.MOD_ID, "leech_layer"), "main");
}