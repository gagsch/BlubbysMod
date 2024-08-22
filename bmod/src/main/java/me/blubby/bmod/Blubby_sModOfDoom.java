package me.blubby.bmod;

import me.blubby.bmod.common.blocks.ModBlocks;
import me.blubby.bmod.common.item.ModItems;
import me.blubby.bmod.server.world.dimension.ModDimensions;
import me.blubby.bmod.server.world.feature.ModConfiguredFeatures;
import me.blubby.bmod.server.world.feature.ModPlacedFeatures;
import me.blubby.bmod.common.events.BlubbySoundEvent;
import me.blubby.bmod.common.events.MobKillEvent;
import me.blubby.bmod.common.events.ModEvents;
import me.blubby.bmod.server.world.structure_types.FloatingLandStructure;
import me.blubby.bmod.server.world.structure_types.StructureTypes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Blubby_sModOfDoom.MOD_ID)
public class Blubby_sModOfDoom {

  // Define mod id in a common place for everything to reference
  public static final String MOD_ID = "blubbysmodofdoom";

  public Blubby_sModOfDoom() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    ModItems.ITEMS.register(modEventBus);
    ModBlocks.BLOCKS.register(modEventBus);

    BlubbySoundEvent.SOUNDS.register(modEventBus);

    modEventBus.addListener(ModEvents::onClientSetup);
    ModEvents.MENU_TYPES.register(modEventBus);

    ModConfiguredFeatures.register(modEventBus);
    ModPlacedFeatures.register(modEventBus);

    ModDimensions.register();

    StructureTypes.register(modEventBus);

    MinecraftForge.EVENT_BUS.register(MobKillEvent.class);
    MinecraftForge.EVENT_BUS.register(this);
  }

  private void commonSetup(final FMLCommonSetupEvent event) {
  }


}