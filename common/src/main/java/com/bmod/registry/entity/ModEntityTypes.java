package com.bmod.registry.entity;

import com.bmod.BlubbysMod;
import com.bmod.registry.ModTags;
import com.bmod.registry.entity.custom.LeechEntity;
import com.bmod.registry.entity.custom.RotFlyEntity;
import com.bmod.registry.entity.custom.BehemothEntity;
import com.bmod.registry.entity.custom.SnowFlinxEntity;
import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.level.entity.SpawnPlacementsRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.Supplier;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.ENTITY_TYPE_REGISTRY);

    public static final Supplier<EntityType<RotFlyEntity>> ROT_FLY = ENTITY_TYPES.register("rot_fly", () -> EntityType.Builder.of(RotFlyEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 0.8f).build("rot_fly"));

    public static final Supplier<EntityType<BehemothEntity>> BEHEMOTH = ENTITY_TYPES.register("behemoth", () -> EntityType.Builder.of(BehemothEntity::new, MobCategory.MONSTER)
                    .sized(1.8f, 3.2f).build("behemoth"));

    public static final Supplier<EntityType<LeechEntity>> LEECH = ENTITY_TYPES.register("leech", () -> EntityType.Builder.of(LeechEntity::new, MobCategory.MONSTER)
            .sized(0.9f, 0.9f).build("leech"));

    public static final Supplier<EntityType<SnowFlinxEntity>> SNOW_FLINX = ENTITY_TYPES.register("snow_flinx", () -> EntityType.Builder.of(SnowFlinxEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 0.7f).build("snow_flinx"));

    public static void initSpawns()
    {
        SpawnPlacementsRegistry.register(ModEntityTypes.BEHEMOTH, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BehemothEntity::checkAnyLightMonsterSpawnRules);
        SpawnPlacementsRegistry.register(ModEntityTypes.ROT_FLY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RotFlyEntity::spawn);
        SpawnPlacementsRegistry.register(ModEntityTypes.LEECH, SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, LeechEntity::checkSurfaceWaterAnimalSpawnRules);

        BiomeModifications.addProperties(mutable -> mutable.hasTag(ModTags.IS_NECROSIS_WEALD), (ctx, mutable) -> {
                mutable.getSpawnProperties().addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.BEHEMOTH.get(), 4, 1, 1));
                mutable.getSpawnProperties().addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.LEECH.get(), 10, 3, 6));
                mutable.getSpawnProperties().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.ROT_FLY.get(), 7, 1, 2));
        });


        SpawnPlacementsRegistry.register(ModEntityTypes.SNOW_FLINX, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SnowFlinxEntity::checkMobSpawnRules);

        BiomeModifications.addProperties(mutable2 -> mutable2.hasTag(BiomeTags.HAS_IGLOO), (ctx, mutable2) ->
                mutable2.getSpawnProperties().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.SNOW_FLINX.get(), 70, 2, 4)));
    }
}
