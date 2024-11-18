package com.bmod.registry.item.custom;

import com.bmod.BlubbysMod;
import com.bmod.util.ItemUtils;
import dev.architectury.platform.Platform;
import net.fabricmc.api.EnvType;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.FormattedCharSink;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
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
        String itemId = ItemUtils.getIdFromItem(stack.getItem());

        makeTooltip(components, itemId);

        if (stack.getItem() instanceof IAccessoryItem)
        {
            components.add(Component.literal("Accessory").withStyle(ChatFormatting.BLUE));
        }

        if (itemId.equals("hot_pepper")) {
            components.add(effect(MobEffects.FIRE_RESISTANCE, 0, 20));
            components.add(effect(MobEffects.MOVEMENT_SPEED, 1, 20));
        }

        super.appendHoverText(stack,level,components,flag);
    }

    public static MutableComponent component(String tooltip) {
        return Component.translatable(tooltip).withStyle(ChatFormatting.GRAY);
    }

    public static MutableComponent effect(MobEffect effect, int amplifier, int effectSeconds) {
        Component effect1 = Component.translatable(effect.getDescriptionId());
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

    public static String formattedCharSequenceToString(FormattedCharSequence formattedCharSequence) {
        StringBuilder stringBuilder = new StringBuilder();

        FormattedCharSink charSink = (index, style, codepoint) -> {
            stringBuilder.appendCodePoint(codepoint);
            return true;
        };

        formattedCharSequence.accept(charSink);

        return stringBuilder.toString();
    }

    public static void makeTooltip(List<Component> components, String itemId) {
        Component tooltip = component("item." + BlubbysMod.MOD_ID + "." + itemId + ".tooltip");

        if (Platform.getEnv() == EnvType.CLIENT && Platform.isFabric() && !Platform.isModLoaded("tooltipfix")) {
            Font font = Minecraft.getInstance().font;

            List<FormattedCharSequence> lines = font.split(FormattedText.of(tooltip.getString()), 170);

            for (FormattedCharSequence line : lines) {
                components.add(component(formattedCharSequenceToString(line)));
            }
        }
        else {
            components.add(tooltip);
        }
    }
}