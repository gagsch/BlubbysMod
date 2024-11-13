package com.bmod;

import com.bmod.client.model.DivineHelmetModel;
import com.bmod.client.renderer.DivineHelmetRenderer;
import com.bmod.client.model.ShroomiteHelmetModel;
import com.bmod.client.renderer.ShroomiteHelmetRenderer;
import com.bmod.client.model.*;
import com.bmod.client.renderer.*;
import com.bmod.event.*;
import com.bmod.event.client.GUIEvent;
import com.bmod.packet.ModPackets;
import com.bmod.registry.block.block_entity.ModBlockEntityTypes;
import com.bmod.registry.enchantment.ModEnchantments;
import com.bmod.registry.entity.custom.*;
import com.bmod.registry.mob_effect.ModMobEffects;
import com.bmod.registry.particle.ModParticles;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.entity.ModEntityTypes;
import com.bmod.registry.item.ModItems;
import com.bmod.registry.ModSounds;
import com.bmod.registry.menu.ModMenus;
import com.bmod.registry.recipe.ModRecipeTypes;
import com.bmod.registry.world.biome.ModBiomes;
import com.bmod.registry.world.feature.ModFeatures;
import com.bmod.registry.world.feature.tree_grower.DreadwoodTreeGrower;
import com.bmod.registry.world.feature.tree_grower.EbonTreeGrower;
import com.bmod.util.TickHandlerUtils;
import com.bmod.util.WoodUtils;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public final class BlubbysMod {
    public static final String MOD_ID = "blubbysmod";

    public static void init() {
        TickHandlerUtils.initialize();
        BlockBreakEvent.initialize();
        PlayerInteractEvent.initialize();
        PlayerJoinEvent.initialize();
        EntityDeathEvent.initialize();
        EntityHurtEvent.initialize();
        CommandRegisterEvent.initialize();
        ModifyLootEvent.initialize();

        ModMenus.MENUS.register();

        WoodUtils.registerWood(WoodUtils.DREADWOOD, new DreadwoodTreeGrower());
        WoodUtils.registerWood(WoodUtils.EBON, new EbonTreeGrower());

        ModEntityTypes.ENTITY_TYPES.register();
        ModBlocks.BLOCKS.register();
        ModBlockEntityTypes.BLOCK_ENTITY_TYPES.register();
        ModItems.ITEMS.register();
        ModEnchantments.ENCHANTMENTS.register();
        ModSounds.SOUNDS.register();
        ModRecipeTypes.RECIPE_SERIALIZERS.register();
        ModRecipeTypes.RECIPE_TYPES.register();
        ModBiomes.BIOMES.register();
        ModMobEffects.MOB_EFFECTS.register();
        ModParticles.initialize();
        ModPackets.initialize();

        ModFeatures.FEATURES.register();
        ModFeatures.init();
        ModEntityTypes.initSpawns();

        EntityAttributeRegistry.register(ModEntityTypes.ROT_FLY, RotFlyEntity::createAttributes);
        EntityAttributeRegistry.register(ModEntityTypes.SPORE_FLY, SporeFlyEntity::createAttributes);
        EntityAttributeRegistry.register(ModEntityTypes.BEHEMOTH, BehemothEntity::createAttributes);
        EntityAttributeRegistry.register(ModEntityTypes.SNOW_FLINX, SnowFlinxEntity::createAttributes);
        EntityAttributeRegistry.register(ModEntityTypes.LEECH, LeechEntity::createAttributes);
        EntityAttributeRegistry.register(ModEntityTypes.DARK_FAIRY, DarkFairyEntity::createAttributes);

        EnvExecutor.runInEnv(Env.CLIENT, () -> Client::initializeClient);
    }

    @Environment(EnvType.CLIENT)
    public static class Client {
        @Environment(EnvType.CLIENT)
        public static void initializeClient() {
            GUIEvent.initialize();

            EntityModelLayerRegistry.register(RotFlyModel.LAYER_LOCATION, RotFlyModel::createBodyLayer);
            EntityModelLayerRegistry.register(SporeFlyModel.LAYER_LOCATION, SporeFlyModel::createBodyLayer);
            EntityModelLayerRegistry.register(SnowFlinxModel.LAYER_LOCATION, SnowFlinxModel::createBodyLayer);
            EntityModelLayerRegistry.register(BehemothModel.LAYER_LOCATION, BehemothModel::createBodyLayer);
            EntityModelLayerRegistry.register(LeechModel.LAYER_LOCATION, LeechModel::createBodyLayer);
            EntityModelLayerRegistry.register(DarkFairyModel.LAYER_LOCATION, DarkFairyModel::createBodyLayer);
            EntityModelLayerRegistry.register(ShroomiteHelmetRenderer.LAYER_LOCATION, ShroomiteHelmetModel::createBodyLayer);
            EntityModelLayerRegistry.register(DivineHelmetRenderer.LAYER_LOCATION, DivineHelmetModel::createBodyLayer);

            EntityRendererRegistry.register(ModEntityTypes.ROT_FLY, RotFlyRenderer::new);
            EntityRendererRegistry.register(ModEntityTypes.SPORE_FLY, SporeFlyRenderer::new);
            EntityRendererRegistry.register(ModEntityTypes.SNOW_FLINX, SnowFlinxRenderer::new);
            EntityRendererRegistry.register(ModEntityTypes.BEHEMOTH, BehemothRenderer::new);
            EntityRendererRegistry.register(ModEntityTypes.LEECH, LeechRenderer::new);
            EntityRendererRegistry.register(ModEntityTypes.DARK_FAIRY, DarkFairyRenderer::new);
        }
    }
}
