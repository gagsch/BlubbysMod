package com.bmod.forge.datagen;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.entity.ModEntities;
import com.bmod.registry.item.ModItems;
import dev.architectury.registry.registries.RegistrySupplier;
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
        add("item.blubbysmodofdoom." + ModItems.CHRONOS_CLOCK.get().builtInRegistryHolder().key().location().getPath() + ".tooltip", "Skips time forward. Avoid skipping ahead to far!");
        add("item.blubbysmodofdoom." + ModItems.LUCKY_ROCK.get().builtInRegistryHolder().key().location().getPath() + ".tooltip", "Increases ore drops by one. Not effective with Silk Touch or solid blocks.\nEffect applied while in inventory.");
        add("item.blubbysmodofdoom." + ModItems.TOTEM_OF_DREAMS.get().builtInRegistryHolder().key().location().getPath() + ".tooltip", "Teleports you to your spawn point on death while retaining items and EXP.");
        add("item.blubbysmodofdoom." + ModItems.ENDER_BUNDLE.get().builtInRegistryHolder().key().location().getPath() + ".tooltip", "Opens your Ender Chest with 3 extra rows, doubling the Ender Chest capacity.");
        add("item.blubbysmodofdoom." + ModItems.HOT_PEPPER.get().builtInRegistryHolder().key().location().getPath() + ".tooltip", "Used to make Flamin' Hot Cheetos™");
        add("item.blubbysmodofdoom." + ModItems.BUBBLE_WAND.get().builtInRegistryHolder().key().location().getPath() + ".tooltip", "Places bubble blocks.");
        add("item.blubbysmodofdoom." + ModItems.VOID_LANTERN.get().builtInRegistryHolder().key().location().getPath() + ".tooltip", "Lights up everything around you.");

        addItem(ModItems.BEHEMOTH_SPAWN_EGG, "Necrotic Behemoth Spawn Egg");
        addItem(ModItems.BLUBBY_COIN, "Penny");
        addItem(ModItems.BUBBLE_WAND, "Bubble Wand");
        addItem(ModItems.DIVINE_ALLOY, "Divine Alloy");
        addItem(ModItems.ENDER_BUNDLE, "Void Bundle");
        addItem(ModItems.ESSENCE_NIGHTMARE_REALM, "Essence of the Nightmare Realm");
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
        addItem(ModItems.SOUL_INFUSED_RUBY, "Soul-Infused Ruby");
        addItem(ModItems.SOUL_SPACE, "Soul of the Cosmos");

        addBlock(ModBlocks.ENRICHMENT_TABLE, "Enrichment Table");

        addEntity(ModEntities.BEHEMOTH.get().builtInRegistryHolder().key().location().getPath(), "Necrotic Behemoth");

        add("itemGroup.blubbysmodofdoom.blubbys_tab_of_doom", "Blubby's Mod of Doom");

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

    public void addItem(Supplier<? extends Item> item, @NotNull String name) {
        add("item.blubbysmodofdoom." + item.get().builtInRegistryHolder().key().location().getPath(), name);
    }

    public void addBlock(Supplier<? extends Block> block, @NotNull String name) {
        add("block.blubbysmodofdoom." + block.get().builtInRegistryHolder().key().location().getPath(), name);
    }

    private void addEntity(String entityId, String name) {
        add("entity.blubbysmodofdoom." + entityId, name);
    }

    private void addRemainingItems() {
        for (Supplier<? extends Item> item : ModItems.ITEMS)
        {
            String key = getPath((RegistrySupplier<Item>) item);

            if (!registeredLang.contains("item.blubbysmodofdoom." + key) && !(item.get() instanceof BlockItem)) {
                add("item.blubbysmodofdoom." + key, convertToName(key));
            } else {
                System.out.println("Lang " + key + " is already registered.");
            }
        }
    }

    private String getPath(Supplier<Item> item)
    {
        return item.get().builtInRegistryHolder().key().location().getPath();
    }

    private void addRemainingEntities() {
        for (RegistrySupplier<EntityType<?>> entity : ModEntities.ENTITY_TYPES)
        {
            String key = entity.getId().getPath();

            if (!registeredLang.contains("entity.blubbysmodofdoom." + key)) {
                add("entity.blubbysmodofdoom." + key, convertToName(key));
            } else {
                System.out.println("Lang " + key + " is already registered.");
            }
        }
    }

    private void addRemainingBlocks() {
        for (RegistrySupplier<Block> block : ModBlocks.BLOCKS)
        {
            String key = block.getId().getPath();

            if (!registeredLang.contains("block.blubbysmodofdoom." + key)) {
                add("block.blubbysmodofdoom." + key, convertToName(key));
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