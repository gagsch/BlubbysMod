package com.bmod.forge.datagen;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.item.ModItems;
import com.bmod.registry.item.custom.BubbleWandItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static com.bmod.util.WoodUtils.*;

public class ModItemModelProvider extends ItemModelProvider {
    private final Set<String> registeredItems = new HashSet<>();

    private static final List<Class<?>> HANDHELD_CLASS = Arrays.asList(
            SwordItem.class,
            PickaxeItem.class,
            AxeItem.class,
            ShovelItem.class,
            HoeItem.class,
            BubbleWandItem.class
    );

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, BlubbysMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        specialBlockItem(sapling(DREADWOOD));
        specialBlockItem(sapling(EBON));
        specialBlockItem(ModBlocks.GLEAM_SHROOM);

        simpleItem(ModItems.HOT_PEPPER_SEEDS);
        simpleHandheldItem(ModItems.BUBBLE_WAND);

        spawnEggItem(ModItems.ROT_FLY_SPAWN_EGG);
        spawnEggItem(ModItems.BEHEMOTH_SPAWN_EGG);
        spawnEggItem(ModItems.SNOW_FLINX_SPAWN_EGG);

        // registeredItems.add(getPath(ModItems.SOUL_DIMENSIONS.get()));

        registerUnsetItems();
    }

    private void registerUnsetItems()
    {
        for (Supplier<Item> item : ModItems.ITEMS)
        {
            String path = getPath(item.get());
            if (!registeredItems.contains(path) && !(item.get() instanceof BlockItem)) {
                if (HANDHELD_CLASS.stream().anyMatch(cls -> cls.isInstance(item.get())))
                {
                    simpleHandheldItem(item);
                } else
                {
                    simpleItem(item);
                }

            } else {
                System.out.println("Item " + path + " is already registered.");
            }
        }
    }

    private String getPath(Item item)
    {
        return item.builtInRegistryHolder().key().location().getPath();
    }

    private void simpleItem(Supplier<Item> item)
    {
        String path = getPath(item.get());
        registeredItems.add(path);

        withExistingParent(getPath(item.get()),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(BlubbysMod.MOD_ID, "items/" + getPath(item.get())));
    }

    private void simpleHandheldItem(Supplier<Item> item)
    {
        String path = getPath(item.get());
        registeredItems.add(path);

        withExistingParent(getPath(item.get()),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(BlubbysMod.MOD_ID, "items/" + getPath(item.get())));
    }

    public void specialBlockItem(Supplier<Block> block) {
        String path = getPath(block.get().asItem());
        registeredItems.add(path);

        withExistingParent(getPath(block.get().asItem()),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(BlubbysMod.MOD_ID, "block/" + getPath(block.get().asItem())));
    }

    public void spawnEggItem(Supplier<Item> item) {
        String path = getPath(item.get());
        registeredItems.add(path);

        withExistingParent(getPath(item.get()),
                mcLoc("item/template_spawn_egg"));
    }
}