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

public class ENLangProvider extends LanguageProvider {
    private static final String NORMAL_CHARS = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_,;.?!/\\'";
    private static final String UPSIDE_DOWN_CHARS = " ɐqɔpǝɟbɥıظʞןɯuodbɹsʇnʌʍxʎzⱯᗺƆᗡƎℲ⅁HIſʞꞀWNOԀὉᴚS⟘∩ΛMXʎZ0ƖᄅƐㄣϛ9ㄥ86‾'؛˙¿¡/\\,";
    private final boolean upsideDown;

    private final Set<String> registeredLang = new HashSet<>();

    public ENLangProvider(DataGenerator pGenerator, String locale, boolean upsideDown) {
        super(pGenerator, BlubbysMod.MOD_ID, locale);
        this.upsideDown = upsideDown;
    }

    @Override
    protected void addTranslations() {
        add("item.blubbysmod." + getIdFromItem(ModItems.CHRONOS_CLOCK) + ".tooltip",
                "Skips time forward. Avoid skipping ahead to far!");

        add("item.blubbysmod." + getIdFromItem(ModItems.CHRONOS_STOPWATCH) + ".tooltip",
                "Marks your location on first use, then returns you to that spot on the second use.");

        add("item.blubbysmod." + getIdFromItem(ModItems.LUCKY_ROCK) + ".tooltip",
                "Increases ore drops by one. Not effective with Silk Touch.");

        add("item.blubbysmod." + getIdFromItem(ModItems.MYSTIC_EMBER) + ".tooltip",
                "Attacked enemies are set ablaze.");

        add("item.blubbysmod." + getIdFromItem(ModItems.LAVA_RING) + ".tooltip",
                "Makes you immune to fire.");

        add("item.blubbysmod." + getIdFromItem(ModItems.MYSTIC_MOLTEN_RING) + ".tooltip",
                "Gives the effect of both the Mystic Ember and the Lava Ring.");

        add("item.blubbysmod." + getIdFromItem(ModItems.DEMON_GLOVES) + ".tooltip",
                "You have the power of the Mystic Ember, Lava Ring, and Vampire Gloves. Additionally, you regenerate faster on fire.");

        add("item.blubbysmod." + getIdFromItem(ModItems.ETERNAL_SATCHEL) + ".tooltip",
                "You do not lose your accessories on death.");

        add("item.blubbysmod." + getIdFromItem(ModItems.VAMPIRE_GLOVES) + ".tooltip",
                "Steals life from enemies.");

        add("item.blubbysmod." + getIdFromItem(ModItems.TOTEM_OF_DREAMS) + ".tooltip",
                "Teleports you to your spawn point on death while retaining items and EXP.");

        add("item.blubbysmod." + getIdFromItem(ModItems.WIND_ROCKET) + ".tooltip",
                "This rocket uses the air around you to propel you forward in flight.");

        add("item.blubbysmod." + getIdFromItem(ModItems.VOID_BUNDLE) + ".tooltip",
                "Opens your Ender Chest with 3 extra rows, doubling the Ender Chest capacity.");

        add("item.blubbysmod." + getIdFromItem(ModItems.HOT_PEPPER) + ".tooltip",
                "Used to make Flamin' Hot Cheetos™");

        add("item.blubbysmod." + getIdFromItem(ModItems.BUBBLE_WAND) + ".tooltip",
                "Places bubble blocks.");

        add("item.blubbysmod." + getIdFromItem(ModItems.CURSED_GEM) + ".tooltip",
                "Used to activate the Nightmare Gateway.");

        add("item.blubbysmod." + getIdFromItem(ModItems.ANCIENT_RECIPE_BOOK) + ".tooltip",
                "The Guide to the Enrichment Table.");

        add("item.blubbysmod." + getIdFromItem(ModItems.ANCIENT_RECIPE_PAGE) + ".tooltip",
                "When used, unlocks a page in the Ancient Recipe Book.");

        addItem(ModItems.BEHEMOTH_SPAWN_EGG, "Necrotic Behemoth Spawn Egg");
        addItem(ModItems.BLUBBY_COIN, "Penny");
        addItem(ModItems.BUBBLE_WAND, "Bubble Wand");
        addItem(ModItems.DIVINE_ALLOY, "Divine Alloy");
        addItem(ModItems.VOID_BUNDLE, "Void Bundle");
        addItem(ModItems.ESSENCE_END, "Essence of the End");
        addItem(ModItems.ESSENCE_NETHER, "Essence of the Nether");
        addItem(ModItems.ESSENCE_NIGHT, "Essence of the Night");
        addItem(ModItems.ESSENCE_OVERWORLD, "Essence of the Overworld");
        addItem(ModItems.ESSENCE_SEA, "Essence of the Sea");
        addItem(ModItems.ESSENCE_VOID, "Essence of the Void");
        addItem(ModItems.HOT_PEPPER_SEEDS, "Fiery Hot Pepper Seeds");
        addItem(ModItems.HOT_PEPPER, "Fiery Hot Pepper");
        addItem(ModItems.SOUL_DUST, "Soul Dust");
        addItem(ModItems.SOUL_ELEMENTS, "Soul of the Elements");
        addItem(ModItems.SOUL_FRAGMENT, "Soul Fragment");
        addItem(ModItems.SOUL_SPACE, "Soul of the Cosmos");

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
        if(name.contains("Essence")) name = name.replace("Essence", "Essence of");
        if(name.contains("Soul")) name = name.replace("Soul", "Soul of");

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