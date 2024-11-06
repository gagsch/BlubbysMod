package com.bmod.forge.datagen;

import com.bmod.BlubbysMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BlubbysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new ModLangProvider(generator, "en_us", false));
        generator.addProvider(event.includeClient(), new ModLangProvider(generator, "en_ud", true));
        generator.addProvider(event.includeServer(), new ModRecipeProvider(generator));
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(generator, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModLootTableProvider(generator));
        ModBlockTagsProvider modBlockTagsProvider = new ModBlockTagsProvider(generator, existingFileHelper);
        generator.addProvider(event.includeServer(), modBlockTagsProvider);
        generator.addProvider(event.includeServer(), new ModItemTagsProvider(generator, modBlockTagsProvider, existingFileHelper));
    }
}
