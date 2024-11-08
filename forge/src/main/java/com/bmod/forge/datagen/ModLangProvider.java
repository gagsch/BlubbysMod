package com.bmod.forge.datagen;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.entity.ModEntityTypes;
import com.bmod.registry.item.ModItems;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Holder;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import static com.bmod.util.ItemUtils.getIdFromItem;

public class ModLangProvider extends LanguageProvider {
    private static final String NORMAL_CHARS = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_,;.?!/\\'";
    private static final String UPSIDE_DOWN_CHARS = " ɐqɔpǝɟbɥıظʞןɯuodbɹsʇnʌʍxʎzⱯᗺƆᗡƎℲ⅁HIſʞꞀWNOԀὉᴚS⟘∩ΛMXʎZ0ƖᄅƐㄣϛ9ㄥ86‾'؛˙¿¡/\\,";
    private final boolean upsideDown;

    private final Set<String> registeredLang = new HashSet<>();

    public ModLangProvider(DataGenerator pGenerator, String locale, boolean upsideDown) {
        super(pGenerator, BlubbysMod.MOD_ID, locale);
        this.upsideDown = upsideDown;
    }

    @Override
    protected void addTranslations() {
        addTooltip(ModItems.CHRONOS_CLOCK, "Skips time forward. Avoid skipping ahead too far!");
        addTooltip(ModItems.CHRONOS_STOPWATCH, "Marks your location on first use, then returns you to that spot on the second use.");
        addTooltip(ModItems.LUCKY_ROCK, "Increases ore drops by one. Not effective with Silk Touch.");
        addTooltip(ModItems.HASTY_CHISEL, "You dig much faster.");
        addTooltip(ModItems.LUCKY_CHISEL, "Gives the effect of both the Hasty Chisel and the Lucky Rock. Additionally, you gain the Luck effect.");
        addTooltip(ModItems.MYSTIC_EMBER, "Attacked enemies are set ablaze.");
        addTooltip(ModItems.LAVA_RING, "Makes you immune to fire.");
        addTooltip(ModItems.MYSTIC_MOLTEN_RING, "Gives the effect of both the Mystic Ember and the Lava Ring.");
        addTooltip(ModItems.DEMON_GLOVES, "You have the power of the Mystic Ember, Lava Ring, and Vampire Gloves. Additionally, you regenerate faster on fire.");
        addTooltip(ModItems.ETERNAL_SATCHEL, "You do not lose your accessories on death.");
        addTooltip(ModItems.VAMPIRE_GLOVES, "Steals life from enemies.");
        addTooltip(ModItems.TOTEM_OF_DREAMS, "Teleports you to your spawn point on death while retaining items and EXP.");
        addTooltip(ModItems.WIND_ROCKET, "This rocket uses the air around you to propel you forward in flight.");
        addTooltip(ModItems.VOID_BUNDLE, "Opens your Ender Chest with 3 extra rows, doubling the Ender Chest capacity.");
        addTooltip(ModItems.HOT_PEPPER, "Used to make Flamin' Hot Cheetos™");
        addTooltip(ModItems.BUBBLE_WAND, "Places bubble blocks, which can be passed through by solids but not by liquids.");
        addTooltip(ModItems.CURSED_GEM, "Used to activate the Nightmare Gateway.");
        addTooltip(ModItems.ANCIENT_GUIDE_BOOK, "The Guide to Blubby's Mod");
        addTooltip(ModItems.IRON_RING, "Maybe something could be made out of this.");
        addTooltip(ModItems.LEATHER_GLOVES, "Maybe something could be made out of this.");
        addTooltip(ModItems.HEART_NECKLACE, "Increases max health by 1 heart.");
        addTooltip(ModItems.BAND_OF_REGENERATION, "Increases health regeneration.");

        addItem(ModItems.ANCIENT_GUIDE_BOOK, "Ancient Guide");
        addItem(ModItems.BEHEMOTH_SPAWN_EGG, "Necrotic Behemoth Spawn Egg");
        addItem(ModItems.BLUBBY_COIN, "Penny");
        addItem(ModItems.VOID_BUNDLE, "Void Bundle");
        addItem(ModItems.HOT_PEPPER_SEEDS, "Fiery Hot Pepper Seeds");
        addItem(ModItems.HOT_PEPPER, "Fiery Hot Pepper");


        addBlock(ModBlocks.WORKSHOP, "Artisan's Workshop");

        add("enchantment.blubbysmod.web_walker", "Web Walker");

        add("effect.blubbysmod.mycosis", "Mycosis");

        addEntity(ModEntityTypes.BEHEMOTH.get(), "Necrotic Behemoth");

        add("itemGroup.blubbysmod.blubbys_tab_of_doom", "Blubby's Mod");

        addRemainingItems();
        addRemainingBlocks();
        addRemainingEntities();
    }

    @Override
    public void add(@NotNull String key, @NotNull String value) {
        registeredLang.add(key);
        if(upsideDown) super.add(key, toUpsideDown(value));
        else super.add(key, value);
    }

    public void addItem(@NotNull Supplier<? extends Item> item, @NotNull String name) {
        add("item.blubbysmod." + getIdFromItem(item), name);
    }

    public void addTooltip(@NotNull Supplier<? extends Item> item, @NotNull String tooltip) {
        add("item.blubbysmod." + getIdFromItem(item) + ".tooltip", tooltip);
    }

    public void addBlock(@NotNull Supplier<? extends Block> block, @NotNull String name) {
        add("block.blubbysmod." + getIdFromItem(Holder.direct(block.get().asItem())), name);
    }

    private void addEntity(EntityType<?> entity, String name) {
        add("entity.blubbysmod." + entity.builtInRegistryHolder().key().location().getPath(), name);
    }

    private void addRemainingItems() {
        for (Supplier<Item> item : ModItems.ITEMS)
        {
            String key = getIdFromItem(item);

            if (!registeredLang.contains("item.blubbysmod." + key) && !(item.get() instanceof BlockItem)) {
                add("item.blubbysmod." + key, convertToName(key));
            } else {
                System.out.println("Lang " + key + " is already registered.");
            }
        }
    }

    private void addRemainingEntities() {
        for (RegistrySupplier<EntityType<?>> entity : ModEntityTypes.ENTITY_TYPES)
        {
            String key = entity.get().builtInRegistryHolder().key().location().getPath();

            if (!registeredLang.contains("entity.blubbysmod." + key)) {
                add("entity.blubbysmod." + key, convertToName(key));
            } else {
                System.out.println("Lang " + key + " is already registered.");
            }
        }
    }

    private void addRemainingBlocks() {
        for (RegistrySupplier<Block> block : ModBlocks.BLOCKS)
        {
            String key = block.getId().getPath();

            if (!registeredLang.contains("block.blubbysmod." + key)) {
                add("block.blubbysmod." + key, convertToName(key));
            } else {
                System.out.println("Lang " + key + " is already registered.");
            }
        }
    }

    private String convertToName(String key) {
        String name;
        StringBuilder builder = new StringBuilder(key.substring(0, 1).toUpperCase() + key.substring(1));

        for(int i = 1; i < builder.length(); i++) {
            if(builder.charAt(i) == '_') {
                builder.deleteCharAt(i);
                builder.replace(i, i + 1, " " + Character.toUpperCase(builder.charAt(i)));
            }
        }

        name = builder.toString();

        if(name.contains("Blessed")) name = name.replace("Blessed", "Divine");
        if(name.contains("Of")) name = name.replace("Of", "of");
        if(name.contains("The")) name = name.replace("The", "the");

        if (upsideDown)
        {
            name = new StringBuilder(name).reverse().toString();
        }

        return upsideDown ? toUpsideDown(name) : name;
    }

    private static String toUpsideDown(String name) {
        StringBuilder builder = new StringBuilder();

        for (int i = name.length() - 1; i >= 0; i--) {
            int normalCharIndex = NORMAL_CHARS.indexOf(name.charAt(i));
            if (normalCharIndex != -1) {
                char upsideDown = UPSIDE_DOWN_CHARS.charAt(normalCharIndex);
                builder.append(upsideDown);
            } else {
                builder.append(name.charAt(i));
            }
        }

        return builder.toString();
    }
}