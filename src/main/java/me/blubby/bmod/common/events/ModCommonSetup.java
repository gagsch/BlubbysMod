package me.blubby.bmod.common.events;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.client.events.ModSounds;
import me.blubby.bmod.common.block.ModBlocks;
import me.blubby.bmod.common.entity.ModEntities;
import me.blubby.bmod.common.entity.custom.BehemothEntity;
import me.blubby.bmod.common.entity.custom.RotFlyEntity;
import me.blubby.bmod.common.entity.custom.SnowFlinxEntity;
import me.blubby.bmod.common.item.ModItems;
import me.blubby.bmod.common.world.ModDimensions;
import me.blubby.bmod.common.world.feature.ModFeatures;
import me.blubby.bmod.common.world.feature.tree_grower.DreadwoodTreeGrower;
import me.blubby.bmod.common.world.feature.tree_grower.EbonTreeGrower;
import me.blubby.bmod.core.util.WoodUtils;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Blubby_sModOfDoom.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCommonSetup {

    public static void ModEvents(IEventBus modEventBus)
    {
        WoodUtils.registerWood(WoodUtils.DREADWOOD, new DreadwoodTreeGrower());
        WoodUtils.registerWood(WoodUtils.EBON, new EbonTreeGrower());

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModEntities.register(modEventBus);

        ModSounds.SOUNDS.register(modEventBus);

        ModFeatures.register(modEventBus);

        ModDimensions.register();
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ROT_FLY.get(), RotFlyEntity.createAttributes().build());
        event.put(ModEntities.BEHEMOTH.get(), BehemothEntity.createAttributes().build());
        event.put(ModEntities.SNOW_FLINX.get(), SnowFlinxEntity.createAttributes().build());
    }
}