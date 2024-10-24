package com.bmod.registry.item.custom;

import com.bmod.BlubbysMod;
import com.bmod.util.ItemUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ToolTipItem extends Item {

    public ToolTipItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        String itemName = ItemUtils.getIdFromItem(stack.getItem());

        components.add(component("item." + BlubbysMod.MOD_ID + "." + itemName + ".tooltip"));

        switch (itemName)
        {
            case "lucky_rock":
                components.add(effect(MobEffects.LUCK, 4, 0));
                break;
            case "hot_pepper":
                components.add(effect(MobEffects.FIRE_RESISTANCE, 0, 20));
                components.add(effect(MobEffects.MOVEMENT_SPEED, 1, 20));
                break;
            case "cursed_gem":
                CompoundTag tag = stack.getOrCreateTag();

                String playerName = "Nobody";

                if (level != null && tag.hasUUID(BlubbysMod.MOD_ID + ":player_link"))
                {
                    Player player = level.getPlayerByUUID(tag.getUUID(BlubbysMod.MOD_ID + ":player_link"));

                    if (player!=null)
                    {
                        playerName = player.getName().getString();
                    }
                }

                components.add(Component.literal("Linked to: " + playerName).withStyle(ChatFormatting.YELLOW));
                break;
            case "ancient_recipe_book", "ancient_recipe_page":
                components.add(Component.literal("Pages discovered: " + stack.getOrCreateTag().getInt(BlubbysMod.MOD_ID + ":pages")).withStyle(ChatFormatting.YELLOW));
                break;
        }

        super.appendHoverText(stack,level,components,flag);
    }

    public MutableComponent component(String tooltip) {
        return Component.translatable(tooltip).withStyle(ChatFormatting.GRAY);
    }

    public MutableComponent effect(MobEffect effects, int amplifier, int effectSeconds) {
        Component effect1 = Component.translatable(effects.getDescriptionId());
        Component effect2 = Component.translatable("potion.potency." + amplifier);

        String time = "";

        if (effectSeconds != 0)
        {
            int minutes = effectSeconds / 60;
            int seconds = effectSeconds % 60;

            String parenthesis = amplifier == 0 ? "(" : " (";
            time = parenthesis + minutes + ":" + seconds + ")";
        }

        String effectToolTip = effect1.getString() + " " + effect2.getString() + time;
        return Component.literal(effectToolTip).withStyle(ChatFormatting.BLUE);
    }
}