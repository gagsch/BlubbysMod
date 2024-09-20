package com.bmod.registry.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public class ToolTipItem extends Item {
    public enum ToolTips {
        totem_of_dreams,
        lucky_rock,
        ender_bundle,
        rift_key,
        chronos_clock,
        hot_pepper,
        bubble_wand
    };

    ToolTips toolTip;
    HashMap<ToolTips, MutableComponent> toolTipHashMap = new HashMap<>();

    public ToolTipItem(Item.Properties properties, ToolTips toolTip) {
        super(properties);
        this.toolTip = toolTip;
    }

    public void writeToolTips()
    {
        toolTipHashMap.put(ToolTips.totem_of_dreams, component("item.blubbysmodofdoom.totem_of_dreaming.tooltip"));
        toolTipHashMap.put(ToolTips.lucky_rock, component("item.blubbysmodofdoom.lucky_rock.tooltip"));
        toolTipHashMap.put(ToolTips.ender_bundle, component("item.blubbysmodofdoom.ender_bundle.tooltip"));
        toolTipHashMap.put(ToolTips.rift_key, component("item.blubbysmodofdoom.rift_key.tooltip"));
        toolTipHashMap.put(ToolTips.chronos_clock, component("item.blubbysmodofdoom.chronos_clock.tooltip"));
        toolTipHashMap.put(ToolTips.hot_pepper, component("item.blubbysmodofdoom.hot_pepper.tooltip"));
        toolTipHashMap.put(ToolTips.bubble_wand, component("item.blubbysmodofdoom.bubble_wand.tooltip"));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        writeToolTips();

        components.add(toolTipHashMap.get(toolTip));

        switch (toolTip)
        {
            case lucky_rock:
                components.add(effect(MobEffects.LUCK, 4, 0));
                break;
            case hot_pepper:
                components.add(effect(MobEffects.FIRE_RESISTANCE, 0, 20));
                components.add(effect(MobEffects.MOVEMENT_SPEED, 1, 20));
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