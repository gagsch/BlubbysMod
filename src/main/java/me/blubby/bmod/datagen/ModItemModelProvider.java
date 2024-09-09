package me.blubby.bmod.datagen;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.blocks.ModBlocks;
import me.blubby.bmod.common.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

import static me.blubby.bmod.utils.WoodUtils.*;

public class ModItemModelProvider extends ItemModelProvider {
    private final Set<String> registeredItems = new HashSet<>();

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Blubby_sModOfDoom.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        specialBlockItem(sapling(COSMIC_OAK));
        specialBlockItem(sapling(EBON));
        simpleItem(ModItems.HOT_PEPPER_SEEDS);

        spawnEggItem(ModItems.ROT_FLY_SPAWN_EGG);
        spawnEggItem(ModItems.BEHEMOTH_SPAWN_EGG);
        spawnEggItem(ModItems.SNOW_FLINX_SPAWN_EGG);

        registerUnsetItems();
    }

    private void registerUnsetItems()
    {
        for (RegistryObject<Item> item : ModItems.ITEMS.getEntries())
        {
            String path = item.getId().getPath();
            if (!registeredItems.contains(path) && !(item.get() instanceof BlockItem)) {
                simpleItem(item);
            } else {
                System.out.println("Item " + path + " is already registered.");
            }
        }
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item)
    {
        String path = item.getId().getPath();
        registeredItems.add(path);

        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "items/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleHandheldItem(RegistryObject<Item> item)
    {
        String path = item.getId().getPath();
        registeredItems.add(path);

        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "item/" + item.getId().getPath()));
    }

    public ItemModelBuilder specialBlockItem(RegistryObject<Block> item) {
        String path = item.getId().getPath();
        registeredItems.add(path);

        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Blubby_sModOfDoom.MOD_ID,"block/" + item.getId().getPath()));
    }

    public ItemModelBuilder spawnEggItem(RegistryObject<Item> item) {
        String path = item.getId().getPath();
        registeredItems.add(path);

        return withExistingParent(item.getId().getPath(),
                mcLoc("item/template_spawn_egg"));
    }
}