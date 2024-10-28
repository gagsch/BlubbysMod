package com.bmod.registry.world.biome;

import com.bmod.BlubbysMod;
import com.bmod.registry.ModSounds;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.random.Weight;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ModBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.BIOME_REGISTRY);

    public static final Supplier<Biome> SPIDER_DEN = BIOMES.register("spider_den", ModBiomes::spiderDen);
    public static final Supplier<Biome> GLEAMING_MUSHROOMS = BIOMES.register("gleaming_mushrooms", ModBiomes::gleamingMushrooms);
    public static final ResourceKey<Biome> SPIDER_DEN_KEY = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BlubbysMod.MOD_ID, "spider_den"));
    public static final ResourceKey<Biome> GLEAMING_MUSHROOMS_KEY = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BlubbysMod.MOD_ID, "gleaming_mushrooms"));
    public static final ResourceKey<Biome> WEEPING_FOREST_KEY = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BlubbysMod.MOD_ID, "weeping_forest"));
    public static final ResourceKey<Biome> NECROSIS_WEALD_KEY = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BlubbysMod.MOD_ID, "necrosis_weald"));

    public static Biome spiderDen() {
        MobSpawnSettings.Builder mobBuilder = new MobSpawnSettings.Builder();
        MobSpawnSettings.SpawnerData caveSpiderSpawnerData = new MobSpawnSettings.SpawnerData(EntityType.CAVE_SPIDER, Weight.of(50), 4, 4);
        MobSpawnSettings.SpawnerData spiderSpawnerData = new MobSpawnSettings.SpawnerData(EntityType.SPIDER, Weight.of(50), 4, 4);
        mobBuilder.addSpawn(MobCategory.MONSTER, caveSpiderSpawnerData);
        mobBuilder.addSpawn(MobCategory.MONSTER, spiderSpawnerData);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);

        Music music = new Music(SoundEvents.MUSIC_BIOME_CRIMSON_FOREST, 100, 1000, true);

        return biome(0.5F, 0.5F, mobBuilder, biomeBuilder, music, 2636327, 988686, 4138570, 4138570);
    }

    public static Biome gleamingMushrooms() {
        MobSpawnSettings.Builder mobBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);

        biomeBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.CANYON);
        biomeBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE);

        Music music = new Music(SoundEvents.MUSIC_BIOME_CRIMSON_FOREST, 100, 1000, true);

        return biome(0.5F, 0.5F, mobBuilder, biomeBuilder, music, 2636327, 988686, 4138570, 4138570);
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
                        .ambientAdditionsSound(new AmbientAdditionsSettings(ModSounds.BLYDIM_CAVE_NOISE.get(), .00003))
                        .backgroundMusic(music).build())
                .mobSpawnSettings(mobBuilder.build())
                .generationSettings(generationBuilder.build())
                .build();
    }
}
