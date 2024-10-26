package com.bmod.forge.datagen;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.item.ModItems;
import com.bmod.registry.item.custom.BubbleWandItem;
import com.bmod.util.WoodUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static com.bmod.util.WoodUtils.*;

public class ModItemModelProvider extends ItemModelProvider {
    public static final Set<String> registeredItems = new HashSet<>();

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
        specialBlockItem(door(DREADWOOD));
        specialBlockItem(sapling(EBON));
        specialBlockItem(door(EBON));

        specialBlockItem(ModBlocks.GLEAM_SHROOM);

        simpleItem(ModItems.HOT_PEPPER_SEEDS);
        simpleHandheldItem(ModItems.BUBBLE_WAND);

        spawnEggItem(ModItems.ROT_FLY_SPAWN_EGG);
        spawnEggItem(ModItems.BEHEMOTH_SPAWN_EGG);
        spawnEggItem(ModItems.SNOW_FLINX_SPAWN_EGG);
        spawnEggItem(ModItems.LEECH_SPAWN_EGG);

        voodooDoll(ModItems.VOODOO_DOLL);

        registeredItems.add(getPath(ModItems.SOUL_DIMENSIONS.get()));
        registerUnsetItems();
    }

    private void registerUnsetItems()
    {
        for (Supplier<Item> item : ModItems.ITEMS)
        {
            String path = getPath(item.get());
            if (!registeredItems.contains(path)) {
                if (HANDHELD_CLASS.stream().anyMatch(cls -> cls.isInstance(item.get()))) {
                    simpleHandheldItem(item);
                }
                else if (item.get() instanceof BlockItem blockItem && !(blockItem.getBlock() instanceof DoorBlock || blockItem.getBlock() instanceof SaplingBlock)) {
                    if (blockItem.getBlock() instanceof TrapDoorBlock) {
                        blockItem(item, "_bottom");
                    }
                    else if (blockItem.getBlock() instanceof FenceBlock || blockItem.getBlock() instanceof ButtonBlock) {
                        blockItem(item, "_inventory");
                    }
                    else {
                        blockItem(item, "");
                    }
                }
                else {
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

    public void blockItem(Supplier<Item> block, String suffix) {
        String path = getPath(block.get());
        registeredItems.add(path);

        getBuilder(path).parent(getExistingFile(new ResourceLocation(BlubbysMod.MOD_ID, "block/" + path + suffix)));
    }

    public void specialBlockItem(Supplier<? extends Block> block) {
        String path = getPath(block.get().asItem());
        registeredItems.add(path);

        withExistingParent(path,
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(BlubbysMod.MOD_ID, "block/" + path));
    }

    public void spawnEggItem(Supplier<Item> item) {
        String path = getPath(item.get());
        registeredItems.add(path);

        withExistingParent(getPath(item.get()),
                mcLoc("item/template_spawn_egg"));
    }

    public void voodooDoll(Supplier<Item> item) {
        String path = getPath(item.get());
        registeredItems.add(path);

        for (int textures = 0; textures <= 1; textures++) {
            withExistingParent(getPath(item.get()) + "_" + textures,
                    new ResourceLocation("item/generated")).texture("layer0",
                    new ResourceLocation(BlubbysMod.MOD_ID, "items/" + getPath(item.get()) + "_" + textures));
        }

        getBuilder(path)
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(BlubbysMod.MOD_ID, "items/" + path + "_0"))
                .override()
                    .predicate(new ResourceLocation(BlubbysMod.MOD_ID,"pin"), 0)
                    .model(new ModelFile.UncheckedModelFile(modLoc("item/" + path + "_0")))
                    .end()
                .override()
                    .predicate(new ResourceLocation(BlubbysMod.MOD_ID,"pin"), 1)
                    .model(new ModelFile.UncheckedModelFile(modLoc("item/" + path + "_1")))
                    .end();
    }
}