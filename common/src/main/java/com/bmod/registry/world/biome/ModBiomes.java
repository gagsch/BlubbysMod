package com.bmod.registry.world.biome;

import com.bmod.BlubbysMod;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

import net.minecraft.world.level.biome.*;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ModBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.BIOME_REGISTRY);

    public static final Supplier<Biome> LICHEN_CAVES = BIOMES.register("spider_den", ModBiomes::lichenCaves);
    public static final ResourceKey<Biome> LICHEN_CAVES_KEY = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BlubbysMod.MOD_ID, "spider_den"));

    public static Biome lichenCaves() {
        MobSpawnSettings.Builder mobBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder);
        return biome(0.5F, 0.5F, mobBuilder, biomeBuilder, null, 2636327, 988686, 4138570, 4138570);
    }

    private static Biome biome(float temperature, float downFall, MobSpawnSettings.Builder mobBuilder, BiomeGenerationSettings.Builder generationBuilder, @Nullable Music music, int waterColor, int waterFogColor, int grassColor, int foliageColor) {
        return biome(temperature, downFall, waterColor, waterFogColor, grassColor, foliageColor, mobBuilder, generationBuilder, music);
    }

    private static Biome biome(float temperature, float downFall, int waterColor, int waterFogColor, int grassColor, int foliageColor, MobSpawnSettings.Builder mobBuilder, BiomeGenerationSettings.Builder generationBuilder, @Nullable Music music) {
        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.RAIN)
                .temperature(temperature).downfall(downFall)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(waterColor)
                        .waterFogColor(waterFogColor)
                        .grassColorOverride(grassColor)
                        .foliageColorOverride(foliageColor)
                        .fogColor(0)
                        .skyColor(0)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(music).build())
                .mobSpawnSettings(mobBuilder.build())
                .generationSettings(generationBuilder.build())
                .build();
    }
}
