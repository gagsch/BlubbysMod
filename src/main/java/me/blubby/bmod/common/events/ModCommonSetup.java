package me.blubby.bmod.common.events;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.blocks.ModBlocks;
import me.blubby.bmod.common.entity.ModEntities;
import me.blubby.bmod.common.entity.custom.BehemothEntity;
import me.blubby.bmod.common.entity.custom.DimensionTeleporterEntity;
import me.blubby.bmod.common.entity.custom.RotFlyEntity;
import me.blubby.bmod.common.entity.custom.SnowFlinxEntity;
import me.blubby.bmod.common.item.ModItems;
import me.blubby.bmod.server.world.dimension.ModDimensions;
import me.blubby.bmod.server.world.feature.ModConfiguredFeatures;
import me.blubby.bmod.server.world.feature.ModPlacedFeatures;
import me.blubby.bmod.server.world.feature.tree.CosmicOakTreeGrower;
import me.blubby.bmod.server.world.feature.tree.EbonTreeGrower;
import me.blubby.bmod.server.world.structure_types.StructureTypes;
import me.blubby.bmod.utils.WoodUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Blubby_sModOfDoom.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCommonSetup {

    public static void ModEvents(IEventBus modEventBus)
    {
        WoodUtils.registerWood(WoodUtils.COSMIC_OAK, new CosmicOakTreeGrower(), CreativeModeTab.TAB_MISC);
        WoodUtils.registerWood(WoodUtils.EBON, new EbonTreeGrower(), CreativeModeTab.TAB_MISC);

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModEntities.register(modEventBus);

        BlubbySoundEvent.SOUNDS.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        ModDimensions.register();

        StructureTypes.register(modEventBus);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ROT_FLY.get(), RotFlyEntity.createAttributes().build());
        event.put(ModEntities.BEHEMOTH.get(), BehemothEntity.createAttributes().build());
        event.put(ModEntities.SNOW_FLINX.get(), SnowFlinxEntity.createAttributes().build());
        event.put(ModEntities.DIMENSION_TELEPORTER.get(), DimensionTeleporterEntity.createAttributes().build());
    }
}