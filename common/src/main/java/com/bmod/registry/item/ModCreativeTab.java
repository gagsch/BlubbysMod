package com.bmod.registry.item;

import com.bmod.BlubbysMod;
import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public class ModCreativeTab {
    public static final CreativeModeTab BLUBBYS_TAB_OF_DOOM = CreativeTabRegistry.create(
            new ResourceLocation(BlubbysMod.MOD_ID,
            "blubbys_tab_of_doom"),
            () -> new ItemStack(ModItems.SOUL_INFINITY.get())
    );

    /*new CreativeModeTab(1,"blubbys_tab_of_doom") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.SOUL_INFINITY.get());
        }

        @Override
        public void fillItemList(NonNullList<ItemStack> items) {
            super.fillItemList(items);
            // Add vanilla items
            items.add(new ItemStack(Items.DRAGON_EGG));
        }
    };*/
}
