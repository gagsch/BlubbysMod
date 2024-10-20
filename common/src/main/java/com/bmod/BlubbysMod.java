package com.bmod;

import com.bmod.event.*;
import com.bmod.event.client.RenderOverlayEvent;
import com.bmod.packet.ModPackets;
import com.bmod.registry.enchantment.ModEnchantments;
import com.bmod.registry.mob_effect.ModMobEffects;
import com.bmod.registry.recipe.ModRecipeTypes;
import com.bmod.registry.entity.client.*;
import com.bmod.registry.entity.custom.BehemothEntity;
import com.bmod.registry.entity.custom.RotFlyEntity;
import com.bmod.registry.entity.custom.SnowFlinxEntity;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.entity.ModEntityTypes;
import com.bmod.registry.item.ModItems;
import com.bmod.registry.ModSounds;
import com.bmod.registry.menu.ModMenus;
import com.bmod.registry.world.biome.ModBiomes;
import com.bmod.registry.world.feature.ModFeatures;
import com.bmod.registry.world.feature.tree_grower.DreadwoodTreeGrower;
import com.bmod.registry.world.feature.tree_grower.EbonTreeGrower;
import com.bmod.util.TickHandlerUtils;
import com.bmod.util.WoodUtils;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public final class BlubbysMod {
    public static final String MOD_ID = "blubbysmod";

    public static void init() {
        TickHandlerUtils.initialize();
        BlockBreakEvent.initialize();
        EntityDeathEvent.initialize();
        CommandRegisterEvent.initialize();
        PlayerTickEvent.initialize();
        ModifyLootEvent.initialize();

        ModMenus.MENUS.register();

        WoodUtils.registerWood(WoodUtils.DREADWOOD, new DreadwoodTreeGrower());
        WoodUtils.registerWood(WoodUtils.EBON, new EbonTreeGrower());

        ModEntityTypes.ENTITY_TYPES.register();
        ModBlocks.BLOCKS.register();
        ModItems.ITEMS.register();
        ModEnchantments.ENCHANTMENTS.register();
        ModSounds.SOUNDS.register();
        ModRecipeTypes.RECIPE_SERIALIZERS.register();
        ModRecipeTypes.RECIPE_TYPES.register();
        ModBiomes.BIOMES.register();
        ModMobEffects.MOB_EFFECTS.register();
        ModPackets.initialize();

        ModFeatures.init();
        ModEntityTypes.initSpawns();

        EntityAttributeRegistry.register(ModEntityTypes.ROT_FLY, RotFlyEntity::createAttributes);
        EntityAttributeRegistry.register(ModEntityTypes.BEHEMOTH, BehemothEntity::createAttributes);
        EntityAttributeRegistry.register(ModEntityTypes.SNOW_FLINX, SnowFlinxEntity::createAttributes);

        EnvExecutor.runInEnv(Env.CLIENT, () -> Client::initializeClient);
    }

    @Environment(EnvType.CLIENT)
    public static class Client {
        public static void initializeClient() {
            ItemPropertiesRegistry.registerGeneric(new ResourceLocation(MOD_ID, "pin"),
                    (ItemStack stack, ClientLevel world, LivingEntity entity, int seed) -> {
                        if (stack.hasTag() && stack.getTag().contains(MOD_ID + ":pin")) {
                            return stack.getTag().getFloat(MOD_ID + ":pin");
                        }
                        return 0.0F;
                    });

            RenderOverlayEvent.initialize();

            EntityModelLayerRegistry.register(ModModelLayers.ROT_FLY_LAYER, RotFlyModel::createBodyLayer);
            EntityModelLayerRegistry.register(ModModelLayers.SNOW_FLINX_LAYER, SnowFlinxModel::createBodyLayer);
            EntityModelLayerRegistry.register(ModModelLayers.BEHEMOTH_LAYER, BehemothModel::createBodyLayer);

            EntityRendererRegistry.register(ModEntityTypes.ROT_FLY, RotFlyRenderer::new);
            EntityRendererRegistry.register(ModEntityTypes.SNOW_FLINX, SnowFlinxRenderer::new);
            EntityRendererRegistry.register(ModEntityTypes.BEHEMOTH, BehemothRenderer::new);
        }
    }
}
