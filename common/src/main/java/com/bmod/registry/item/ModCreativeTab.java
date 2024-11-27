package com.bmod.registry.item;

import com.bmod.BlubbysMod;
import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab {
    public static final CreativeModeTab BLUBBYS_MOD = CreativeTabRegistry.create(
            new ResourceLocation(BlubbysMod.MOD_ID,
            "blubbys_mod"),
            () -> new ItemStack(ModItems.ANCIENT_GUIDE_BOOK.get())
    );
}
