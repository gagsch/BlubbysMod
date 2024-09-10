package me.blubby.bmod;

import me.blubby.bmod.client.events.ModClientSetup;
import me.blubby.bmod.client.events.RenderOverlayEvent;
import me.blubby.bmod.common.events.ModCommonSetup;
import me.blubby.bmod.common.events.BlockBreakEvent;
import me.blubby.bmod.common.events.EntityDeathEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Blubby_sModOfDoom.MOD_ID)
public class Blubby_sModOfDoom {
  public static final String MOD_ID = "blubbysmodofdoom";

  public Blubby_sModOfDoom() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    ModCommonSetup.ModEvents(modEventBus);
    ModClientSetup.ModClientSetup(modEventBus);

    MinecraftForge.EVENT_BUS.register(EntityDeathEvent.class);
    MinecraftForge.EVENT_BUS.register(BlockBreakEvent.class);
    MinecraftForge.EVENT_BUS.register(this);
  }

  private void commonSetup(final FMLCommonSetupEvent event) { }
}