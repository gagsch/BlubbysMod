package com.bmod.registry.entity;

import com.bmod.BlubbysMod;
import com.bmod.registry.ModTags;
import com.bmod.registry.entity.custom.*;
import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.level.entity.SpawnPlacementsRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.Supplier;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.ENTITY_TYPE_REGISTRY);

    public static final Supplier<EntityType<RotFlyEntity>> ROT_FLY = ENTITY_TYPES.register("rot_fly", () -> EntityType.Builder.of(RotFlyEntity::new, MobCategory.CREATURE)
            .sized(0.6f, 0.8f).clientTrackingRange(8).build("rot_fly"));

    public static final Supplier<EntityType<SporeFlyEntity>> SPORE_FLY = ENTITY_TYPES.register("spore_fly", () -> EntityType.Builder.of(SporeFlyEntity::new, MobCategory.CREATURE)
            .sized(0.6f, 0.8f).clientTrackingRange(8).build("spore_fly"));

    public static final Supplier<EntityType<BehemothEntity>> BEHEMOTH = ENTITY_TYPES.register("behemoth", () -> EntityType.Builder.of(BehemothEntity::new, MobCategory.MONSTER)
            .sized(1.8f, 3.2f).clientTrackingRange(8).build("behemoth"));

    public static final Supplier<EntityType<LeechEntity>> LEECH = ENTITY_TYPES.register("leech", () -> EntityType.Builder.of(LeechEntity::new, MobCategory.MONSTER)
            .sized(0.9f, 0.9f).clientTrackingRange(8).build("leech"));

    public static final Supplier<EntityType<SnowFlinxEntity>> SNOW_FLINX = ENTITY_TYPES.register("snow_flinx", () -> EntityType.Builder.of(SnowFlinxEntity::new, MobCategory.CREATURE)
            .sized(0.6f, 0.7f).clientTrackingRange(8).build("snow_flinx"));

    public static final Supplier<EntityType<DarkFairyEntity>> DARK_FAIRY = ENTITY_TYPES.register("dark_fairy", () -> EntityType.Builder.of(DarkFairyEntity::new, MobCategory.MONSTER)
            .sized(0.4f, 0.4f).clientTrackingRange(10).build("dark_fairy"));

    public static final Supplier<EntityType<MagmoidEntity>> MAGMOID = ENTITY_TYPES.register("magmoid", () -> EntityType.Builder.of(MagmoidEntity::new, MobCategory.MONSTER)
            .sized(1.4F, 2.6F).fireImmune().clientTrackingRange(10).build("magmoid"));

    public static void initSpawns()
    {
        SpawnPlacementsRegistry.register(ModEntityTypes.BEHEMOTH, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BehemothEntity::spawn);
        SpawnPlacementsRegistry.register(ModEntityTypes.MAGMOID, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MagmoidEntity::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(ModEntityTypes.ROT_FLY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RotFlyEntity::spawn);
        SpawnPlacementsRegistry.register(ModEntityTypes.SPORE_FLY, SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (entityType, levelAccessor, mobSpawnType, blockPos, randomSource) -> SporeFlyEntity.spawn(entityType, levelAccessor, blockPos));
        SpawnPlacementsRegistry.register(ModEntityTypes.DARK_FAIRY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DarkFairyEntity::spawn);
        SpawnPlacementsRegistry.register(ModEntityTypes.LEECH, SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, LeechEntity::spawn);
        SpawnPlacementsRegistry.register(ModEntityTypes.SNOW_FLINX, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SnowFlinxEntity::checkMobSpawnRules);

        BiomeModifications.addProperties(mutable -> mutable.hasTag(ModTags.IS_NECROSIS_WEALD), (ctx, mutable) -> {
            mutable.getSpawnProperties().addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.BEHEMOTH.get(), 4, 1, 1));
            mutable.getSpawnProperties().addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.LEECH.get(), 10, 3, 6));
            mutable.getSpawnProperties().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.ROT_FLY.get(), 7, 1, 2));
        });

        BiomeModifications.addProperties(mutable -> mutable.hasTag(ModTags.IS_WEEPING_FOREST), (ctx, mutable) -> mutable.getSpawnProperties().addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.DARK_FAIRY.get(), 1, 1, 1)));

        BiomeModifications.addProperties(mutable2 -> mutable2.hasTag(BiomeTags.HAS_IGLOO), (ctx, mutable2) ->
                mutable2.getSpawnProperties().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.SNOW_FLINX.get(), 60, 2, 4)));

        BiomeModifications.addProperties(mutable2 -> mutable2.hasTag(BiomeTags.IS_NETHER), (ctx, mutable2) ->
                mutable2.getSpawnProperties().addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.MAGMOID.get(), 7, 2, 3)));
    }
}
