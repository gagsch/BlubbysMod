package com.bmod.registry.entity;

import com.bmod.BlubbysMod;
import com.bmod.registry.entity.custom.RotFlyEntity;
import com.bmod.registry.entity.custom.BehemothEntity;
import com.bmod.registry.entity.custom.SnowFlinxEntity;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.ENTITY_TYPE_REGISTRY);

    public static final Supplier<EntityType<RotFlyEntity>> ROT_FLY = ENTITY_TYPES.register("rot_fly", () -> EntityType.Builder.of(RotFlyEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 0.8f).build("rot_fly"));

    public static final Supplier<EntityType<BehemothEntity>> BEHEMOTH = ENTITY_TYPES.register("behemoth", () -> EntityType.Builder.of(BehemothEntity::new, MobCategory.MONSTER)
                    .sized(2f, 3.6f).build("behemoth"));

    public static final Supplier<EntityType<SnowFlinxEntity>> SNOW_FLINX = ENTITY_TYPES.register("snow_flinx", () -> EntityType.Builder.of(SnowFlinxEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 0.7f).build("snow_flinx"));
}
