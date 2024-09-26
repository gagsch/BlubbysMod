package com.bmod.registry;

import com.bmod.BlubbysMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static TagKey<Item> BOSS_CORES;
    public static TagKey<Item> RUBY_ORES;
    public static TagKey<Item> SCARLITE_ORES;
    public static TagKey<Item> NECRIUM_ORES;

    public static TagKey<Biome> IS_NECROSIS_WEALD;
    public static TagKey<Biome> IS_WEEPING_FOREST;
    public static TagKey<Biome> IS_PLAINS;
    public static TagKey<Biome> IS_STONE_PLAINS;
    public static TagKey<Biome> IS_SPIDER_DEN;
    public static TagKey<Biome> IS_BLYDIM_BIOME;

    public static void initialize()
    {
        BOSS_CORES = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BlubbysMod.MOD_ID, "boss_cores"));
        RUBY_ORES = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BlubbysMod.MOD_ID, "ruby_ores"));
        SCARLITE_ORES = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BlubbysMod.MOD_ID, "scarlite_ores"));
        NECRIUM_ORES = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BlubbysMod.MOD_ID, "necrium_ores"));

        IS_WEEPING_FOREST = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BlubbysMod.MOD_ID, "is_weeping_forest"));
        IS_NECROSIS_WEALD = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BlubbysMod.MOD_ID, "is_necrosis_weald"));
        IS_PLAINS = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BlubbysMod.MOD_ID, "is_plains"));
        IS_STONE_PLAINS = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BlubbysMod.MOD_ID, "is_stone_plains"));
        IS_SPIDER_DEN = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BlubbysMod.MOD_ID, "is_spider_den"));
        IS_BLYDIM_BIOME = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BlubbysMod.MOD_ID, "is_blydim_biome"));
    }
}
