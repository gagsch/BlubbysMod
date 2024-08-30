package me.blubby.bmod.datagen;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.blocks.ModBlocks;
import me.blubby.bmod.common.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Blubby_sModOfDoom.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        saplingItem(ModBlocks.COSMIC_OAK_SAPLING);
        saplingItem(ModBlocks.EBON_SAPLING);

        simpleItem(ModItems.BLUBBY_COIN);

        simpleItem(ModItems.CHRONOS_CLOCK);
        simpleItem(ModItems.ENDER_BUNDLE);
        simpleItem(ModItems.BLACK_HOLE);
        simpleItem(ModItems.LUCKY_ROCK);

        simpleItem(ModItems.WARDEN_CORE);
        simpleItem(ModItems.GUARDIAN_CORE);
        simpleItem(ModItems.HEART_OF_THE_ABYSS);
        simpleItem(ModItems.SOUL_DUST);
        simpleItem(ModItems.ECHOING_SOUL_DUST);
        simpleItem(ModItems.LEATHER_SCRAP);
        simpleItem(ModItems.CONCENTRATED_DARK_MATTER);

        simpleItem(ModItems.BLESSED_SOUL_ALLOY);
        simpleItem(ModItems.BLESSED_INGOT);
        simpleItem(ModItems.BLESSED_HELMET);
        simpleItem(ModItems.BLESSED_CHESTPLATE);
        simpleItem(ModItems.BLESSED_LEGGINGS);
        simpleItem(ModItems.BLESSED_BOOTS);

        simpleItem(ModItems.NIGHTMARE_INGOT);
        simpleItem(ModItems.NIGHTMARE_HELMET);
        simpleItem(ModItems.NIGHTMARE_CHESTPLATE);
        simpleItem(ModItems.NIGHTMARE_LEGGINGS);
        simpleItem(ModItems.NIGHTMARE_BOOTS);

        simpleItem(ModItems.NECRIUM_CHUNK);

        simpleItem(ModItems.COSMILITE_CHUNK);
        simpleItem(ModItems.COSMILITE_INGOT);
        simpleItem(ModItems.COSMILITE_HELMET);
        simpleItem(ModItems.COSMILITE_CHESTPLATE);
        simpleItem(ModItems.COSMILITE_LEGGINGS);
        simpleItem(ModItems.COSMILITE_BOOTS);

        simpleItem(ModItems.ESSENCE_BLESSED);
        simpleItem(ModItems.ESSENCE_DARKNESS);
        simpleItem(ModItems.ESSENCE_DAY);
        simpleItem(ModItems.ESSENCE_DEATH);
        simpleItem(ModItems.ESSENCE_DOOM);
        simpleItem(ModItems.ESSENCE_EARTH);
        simpleItem(ModItems.ESSENCE_ENERGY);
        simpleItem(ModItems.ESSENCE_FLAMES);
        simpleItem(ModItems.ESSENCE_INFINITY);
        simpleItem(ModItems.ESSENCE_LIFE);
        simpleItem(ModItems.ESSENCE_LIGHT);
        simpleItem(ModItems.ESSENCE_NETHER);
        simpleItem(ModItems.ESSENCE_NIGHT);
        simpleItem(ModItems.ESSENCE_SEA);
        simpleItem(ModItems.ESSENCE_STONE);
        simpleItem(ModItems.ESSENCE_TUNDRA);
        simpleItem(ModItems.ESSENCE_VOID);

        simpleItem(ModItems.SOUL_BALANCE);
        simpleItem(ModItems.SOUL_ELEMENTS);
        simpleItem(ModItems.SOUL_INFINITY);
        simpleItem(ModItems.SOUL_SPACE);
        simpleItem(ModItems.SOUL_TIME);

        withExistingParent(ModItems.ROT_FLY_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.BEHEMOTH_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item)
    {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "items/" + item.getId().getPath()));
    }

    public ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Blubby_sModOfDoom.MOD_ID,"block/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleHandheldItem(RegistryObject<Item> item)
    {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "item/" + item.getId().getPath()));
    }

}
