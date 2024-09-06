package me.blubby.bmod.common.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
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
        concentrated_dark_matter,
        lucky_rock,
        ender_bundle,
        rift_key,
        chronos_clock
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
        toolTipHashMap.put(ToolTips.concentrated_dark_matter, component("item.blubbysmodofdoom.concentrated_dark_matter.tooltip"));
        toolTipHashMap.put(ToolTips.lucky_rock, component("item.blubbysmodofdoom.lucky_rock.tooltip"));
        toolTipHashMap.put(ToolTips.ender_bundle, component("item.blubbysmodofdoom.ender_bundle.tooltip"));
        toolTipHashMap.put(ToolTips.rift_key, component("item.blubbysmodofdoom.rift_key.tooltip"));
        toolTipHashMap.put(ToolTips.chronos_clock, component("item.blubbysmodofdoom.chronos_clock.tooltip"));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        writeToolTips();

        components.add(toolTipHashMap.get(toolTip));

        super.appendHoverText(stack,level,components,flag);
    }

    public MutableComponent component(String string) {
        return Component.translatable(string).withStyle(ChatFormatting.GRAY);
    }
}
