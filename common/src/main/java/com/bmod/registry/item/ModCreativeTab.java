package com.bmod.registry.item;

import com.bmod.BlubbysMod;
import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab {
    public static final CreativeModeTab BLUBBYS_TAB_OF_DOOM = CreativeTabRegistry.create(new ResourceLocation(BlubbysMod.MOD_ID, "blubbys_tab_of_doom"), () -> new ItemStack(ModItems.SOUL_INFINITY.get()));
}
