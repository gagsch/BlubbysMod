package me.blubby.bmod.common.entity;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.entity.custom.BehemothEntity;
import me.blubby.bmod.common.entity.custom.RotFlyEntity;
import me.blubby.bmod.common.entity.custom.SnowFlinxEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Blubby_sModOfDoom.MOD_ID);

    public static final RegistryObject<EntityType<RotFlyEntity>> ROT_FLY = ENTITY_TYPES.register("rot_fly", () -> EntityType.Builder.of(RotFlyEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 0.8f).build("rot_fly"));

    public static final RegistryObject<EntityType<BehemothEntity>> BEHEMOTH = ENTITY_TYPES.register("behemoth", () -> EntityType.Builder.of(BehemothEntity::new, MobCategory.MONSTER)
                    .sized(2f, 3.6f).build("behemoth"));

    public static final RegistryObject<EntityType<SnowFlinxEntity>> SNOW_FLINX = ENTITY_TYPES.register("snow_flinx", () -> EntityType.Builder.of(SnowFlinxEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 0.7f).build("snow_flinx"));



    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
