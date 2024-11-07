package com.bmod.registry.item.custom;

import com.bmod.registry.item.ModCreativeTab;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlueprintItem extends Item {
    public static final String ACCESSORY = "accessory";
    public static final String SPECIAL_ITEM = "special";
    public static final String RESOURCE = "resource";
    public static final String UPGRADE = "upgrade";
    public static final String UTILITY = "utility";

    public BlueprintItem() {
        super(new Properties()
                .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                .durability(-1)
                .stacksTo(64));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        String string = itemStack.getOrCreateTag().getString("blueprint");

        string = switch (string) {
            case ACCESSORY -> "Accessory";
            case SPECIAL_ITEM -> "Special Item";
            case RESOURCE -> "Resource";
            case UTILITY -> "Utility";
            case UPGRADE -> "Upgrade";
            default -> "Blank";
        };

        Component component = Component.literal(string).withStyle(ChatFormatting.BLUE);
        components.add(component);

        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
