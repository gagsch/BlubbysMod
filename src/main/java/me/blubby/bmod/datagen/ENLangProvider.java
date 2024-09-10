package me.blubby.bmod.datagen;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.blocks.ModBlocks;
import me.blubby.bmod.common.entity.ModEntities;
import me.blubby.bmod.common.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class ENLangProvider extends LanguageProvider {
    private static final String NORMAL_CHARS = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_,;.?!/\\'";
    private static final String UPSIDE_DOWN_CHARS = " ɐqɔpǝɟbɥıظʞןɯuodbɹsʇnʌʍxʎzⱯᗺƆᗡƎℲ⅁HIſʞꞀWNOԀὉᴚS⟘∩ΛMXʎZ0ƖᄅƐㄣϛ9ㄥ86‾'؛˙¿¡/\\,";
    private final boolean upsideDown;

    private final Set<String> registeredLang = new HashSet<>();

    public ENLangProvider(DataGenerator pGenerator, String locale, boolean upsideDown) {
        super(pGenerator, Blubby_sModOfDoom.MOD_ID, locale);
        this.upsideDown = upsideDown;
    }

    @Override
    protected void addTranslations() {
        addToolTip(ModItems.CHRONOS_CLOCK, "Skips time forward. Avoid skipping ahead to far!");
        addToolTip(ModItems.ENDER_BUNDLE, "Opens your Ender Chest with three extra rows.");
        addToolTip(ModItems.LUCKY_ROCK, "Increases ore drops by one. Not effective with Silk Touch or solid blocks.\nEffect applied while in inventory.");
        addToolTip(ModItems.RIFT_KEY, "Summons a mysterious door in front of you...");
        addToolTip(ModItems.TOTEM_OF_DREAMS, "Teleports you to your spawn point on death while retaining items and EXP.");
        addToolTip(ModItems.CONCENTRATED_DARK_MATTER, "Wubba Lubba Dub Dub!!");
        addToolTip(ModItems.HOT_PEPPER, "Used to make Flamin' Hot Cheetos™");
        addToolTip(ModItems.BUBBLE_WAND, "Places bubble blocks.");

        addItem(ModItems.BEHEMOTH_SPAWN_EGG, "Necrotic Behemoth Spawn Egg");
        addItem(ModItems.BLUBBY_COIN, "Penny");
        addItem(ModItems.BUBBLE_WAND, "Bubble Wand");
        addItem(ModItems.DIVINE_ALLOY, "Divine Alloy");
        addItem(ModItems.ECHOING_SOUL_DUST, "Echoing Soul Dust");
        addItem(ModItems.ENDER_BUNDLE, "Void Bundle");
        addItem(ModItems.ESSENCE_BLESSED, "Essence of the Holy");
        addItem(ModItems.ESSENCE_CONVERGENCE, "Essence of the Convergence");
        addItem(ModItems.ESSENCE_DOOM, "Essence of Havoc");
        addItem(ModItems.ESSENCE_END, "Essence of the End");
        addItem(ModItems.ESSENCE_NETHER, "Essence of the Nether");
        addItem(ModItems.ESSENCE_NIGHT, "Essence of the Night");
        addItem(ModItems.ESSENCE_OVERWORLD, "Essence of the Overworld");
        addItem(ModItems.ESSENCE_SEA, "Essence of the Sea");
        addItem(ModItems.ESSENCE_VOID, "Essence of the Void");
        addItem(ModItems.HOT_PEPPER_SEEDS, "Fiery Hot Pepper Seeds");
        addItem(ModItems.HOT_PEPPER, "Fiery Hot Pepper");
        addItem(ModItems.RIFT_KEY, "Cosmic Rift Key");
        addItem(ModItems.SOUL_DUST, "Soul Dust");
        addItem(ModItems.SOUL_ELEMENTS, "Soul of the Elements");
        addItem(ModItems.SOUL_FRAGMENT, "Soul Fragment");
        addItem(ModItems.SOUL_SPACE, "Soul of the Cosmos");

        addEntity(ModEntities.BEHEMOTH.getId(), "Necrotic Behemoth");
        addEntity(ModEntities.DIMENSION_TELEPORTER.getId(), "Mysterious Door");

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

    private void addItem(RegistryObject<Item> item, String name) {
        add("item.blubbysmodofdoom." + item.getId().getPath(), name);
    }

    private void addBlock(RegistryObject<Block> block, String name) {
        add("block.blubbysmodofdoom." + block.getId().getPath(), name);
    }

    private void addEntity(ResourceLocation entityId, String name) {
        add("entity.blubbysmodofdoom." + entityId.getPath(), name);
    }

    private void addToolTip(RegistryObject<Item> item, String toolTip) {
        add("item.blubbysmodofdoom." + item.getId().getPath() + ".tooltip", toolTip);
    }

    private void addRemainingItems() {
        for (RegistryObject<Item> item : ModItems.ITEMS.getEntries())
        {
            String key = item.getId().getPath();

            if (!registeredLang.contains("item.blubbysmodofdoom." + key) && !(item.get() instanceof BlockItem)) {
                add("item.blubbysmodofdoom." + key, convertToName(key));
            } else {
                System.out.println("Lang " + key + " is already registered.");
            }
        }
    }

    private void addRemainingEntities() {
        for (RegistryObject<EntityType<?>> entity : ModEntities.ENTITY_TYPES.getEntries())
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
        for (RegistryObject<Block> block : ModBlocks.BLOCKS.getEntries())
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