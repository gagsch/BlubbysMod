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
    public enum tooltips {
        TOTEM_OF_DREAMS,
        LUCKY_ROCK,
        ENDER_BUNDLE,
        CHRONOS_CLOCK,
        HOT_PEPPER,
        BUBBLE_WAND,
        VOODOO_DOLL
    };

    tooltips toolTip;
    HashMap<tooltips, MutableComponent> toolTipHashMap = new HashMap<>();

    public ToolTipItem(Properties properties, tooltips toolTip) {
        super(properties);
        this.toolTip = toolTip;
    }

    public void writeToolTips()
    {
        toolTipHashMap.put(tooltips.TOTEM_OF_DREAMS, component("item.blubbysmod.totem_of_dreaming.tooltip"));
        toolTipHashMap.put(tooltips.LUCKY_ROCK, component("item.blubbysmod.lucky_rock.tooltip"));
        toolTipHashMap.put(tooltips.ENDER_BUNDLE, component("item.blubbysmod.ender_bundle.tooltip"));
        toolTipHashMap.put(tooltips.CHRONOS_CLOCK, component("item.blubbysmod.chronos_clock.tooltip"));
        toolTipHashMap.put(tooltips.HOT_PEPPER, component("item.blubbysmod.hot_pepper.tooltip"));
        toolTipHashMap.put(tooltips.BUBBLE_WAND, component("item.blubbysmod.bubble_wand.tooltip"));
        toolTipHashMap.put(tooltips.VOODOO_DOLL, component("item.blubbysmod.cursed_voodoo_doll.tooltip"));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        writeToolTips();

        components.add(toolTipHashMap.get(toolTip));

        switch (toolTip)
        {
            case LUCKY_ROCK:
                components.add(effect(MobEffects.LUCK, 4, 0));
                break;
            case HOT_PEPPER:
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