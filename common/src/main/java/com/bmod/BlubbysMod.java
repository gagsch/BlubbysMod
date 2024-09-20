package com.bmod;

import com.bmod.registry.entity.custom.BehemothEntity;
import com.bmod.registry.entity.custom.RotFlyEntity;
import com.bmod.registry.entity.custom.SnowFlinxEntity;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.entity.ModEntities;
import com.bmod.registry.item.ModItems;
import com.bmod.registry.ModSounds;
import com.bmod.registry.world.feature.ModOreFeatures;
import com.bmod.registry.world.feature.ModTreeFeatures;
import com.bmod.registry.world.feature.ModVegetationFeatures;
import com.bmod.registry.world.feature.tree_grower.DreadwoodTreeGrower;
import com.bmod.registry.world.feature.tree_grower.EbonTreeGrower;
import com.bmod.util.TickHandlerUtils;
import com.bmod.util.WoodUtils;
import dev.architectury.registry.level.entity.EntityAttributeRegistry;

import static com.bmod.registry.menu.ModMenus.MENUS;

public final class BlubbysMod {
    public static final String MOD_ID = "blubbysmodofdoom";

    public static void init() {
        TickHandlerUtils.initialize();

        MENUS.register();

        WoodUtils.registerWood(WoodUtils.DREADWOOD, new DreadwoodTreeGrower());
        WoodUtils.registerWood(WoodUtils.EBON, new EbonTreeGrower());

        ModItems.ITEMS.register();
        ModBlocks.BLOCKS.register();
        ModEntities.ENTITY_TYPES.register();
        ModSounds.SOUNDS.register();

        ModOreFeatures.initialize();
        ModTreeFeatures.initialize();
        ModVegetationFeatures.initialize();

        EntityAttributeRegistry.register(ModEntities.ROT_FLY, RotFlyEntity::createAttributes);
        EntityAttributeRegistry.register(ModEntities.BEHEMOTH, BehemothEntity::createAttributes);
        EntityAttributeRegistry.register(ModEntities.SNOW_FLINX, SnowFlinxEntity::createAttributes);
    }
}
