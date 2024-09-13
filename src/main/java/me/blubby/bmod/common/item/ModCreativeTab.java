package me.blubby.bmod.common.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab {
    public static final CreativeModeTab BLUBBYS_TAB_OF_DOOM = new CreativeModeTab("blubbys_tab_of_doom") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SOUL_INFINITY.get());
        }
    };
}
