package com.bmod;

import com.bmod.events.*;
import com.bmod.events.client.RenderOverlayEvent;
import com.bmod.registry.enchantment.ModEnchantments;
import com.bmod.registry.mob_effect.ModMobEffects;
import com.bmod.registry.recipe.ModRecipeTypes;
import com.bmod.registry.entity.client.*;
import com.bmod.registry.entity.custom.BehemothEntity;
import com.bmod.registry.entity.custom.RotFlyEntity;
import com.bmod.registry.entity.custom.SnowFlinxEntity;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.entity.ModEntities;
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
        UseBlockEvent.initialize();
        BlockBreakEvent.initialize();
        EntityDeathEvent.initialize();
        CommandRegisterEvent.initialize();
        PlayerTickEvent.initialize();

        ModMenus.MENUS.register();

        WoodUtils.registerWood(WoodUtils.DREADWOOD, new DreadwoodTreeGrower());
        WoodUtils.registerWood(WoodUtils.EBON, new EbonTreeGrower());

        ModEntities.ENTITY_TYPES.register();
        ModBlocks.BLOCKS.register();
        System.out.println("Registering Items");
        ModItems.ITEMS.register();
        ModEnchantments.ENCHANTMENTS.register();
        ModSounds.SOUNDS.register();
        ModRecipeTypes.RECIPE_SERIALIZERS.register();
        ModRecipeTypes.RECIPE_TYPES.register();
        ModBiomes.BIOMES.register();
        ModMobEffects.MOB_EFFECTS.register();

        ModFeatures.init();

        EntityAttributeRegistry.register(ModEntities.ROT_FLY, RotFlyEntity::createAttributes);
        EntityAttributeRegistry.register(ModEntities.BEHEMOTH, BehemothEntity::createAttributes);
        EntityAttributeRegistry.register(ModEntities.SNOW_FLINX, SnowFlinxEntity::createAttributes);

        EnvExecutor.runInEnv(Env.CLIENT, () -> Client::initializeClient);
    }

    @Environment(EnvType.CLIENT)
    public static class Client {
        public static void initializeClient() {
            System.out.println("Registering Predicate");
            ItemPropertiesRegistry.registerGeneric(new ResourceLocation("blubbysmod", "pin"),
                    (ItemStack stack, ClientLevel world, LivingEntity entity, int seed) -> {
                        if (stack.hasTag() && stack.getTag().contains("blubbysmod:pin")) {
                            return stack.getTag().getFloat("blubbysmod:pin");
                        }
                        return 0.0F;
                    });

            RenderOverlayEvent.initialize();

            EntityModelLayerRegistry.register(ModModelLayers.ROT_FLY_LAYER, RotFlyModel::createBodyLayer);
            EntityModelLayerRegistry.register(ModModelLayers.SNOW_FLINX_LAYER, SnowFlinxModel::createBodyLayer);
            EntityModelLayerRegistry.register(ModModelLayers.BEHEMOTH_LAYER, BehemothModel::createBodyLayer);

            EntityRendererRegistry.register(ModEntities.ROT_FLY, RotFlyRenderer::new);
            EntityRendererRegistry.register(ModEntities.SNOW_FLINX, SnowFlinxRenderer::new);
            EntityRendererRegistry.register(ModEntities.BEHEMOTH, BehemothRenderer::new);
        }
    }
}
