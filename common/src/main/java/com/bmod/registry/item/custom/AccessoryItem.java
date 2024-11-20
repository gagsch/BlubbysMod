package com.bmod.registry.item.custom;

import com.bmod.registry.item.ModCreativeTab;
import com.bmod.util.ContainerUtils;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class AccessoryItem extends ToolTipItem {
    public static Properties DEFAULT_ACCESSORY_PROPERTIES = new Properties()
            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
            .durability(-1)
            .stacksTo(1)
            .fireResistant();

    public AccessoryItem() {
        super(DEFAULT_ACCESSORY_PROPERTIES);
    }

    public void serverAccessoryTick(ServerLevel level, ServerPlayer player) { }

    public void localAccessoryTick(Level level, Player player) { }

    public void setAttribute(ServerPlayer player, Attribute attribute, double value, AttributeModifier.Operation operation)
    {
        UUID uuid = new UUID(this.hashCode(), 1);

        AttributeInstance attributeInstance = player.getAttribute(attribute);

        if (attributeInstance != null) {
            AttributeModifier existingModifier = attributeInstance.getModifier(uuid);

            if (existingModifier != null) {
                if (existingModifier.getAmount() == value && existingModifier.getOperation() == operation) {
                    return;
                }
            }

            AttributeModifier newModifier = new AttributeModifier(uuid, "accessory", value, operation);
            attributeInstance.addTransientModifier(newModifier);
        }
    }

    public static void clearAttributes(ServerPlayer player)
    {
        for (Attribute attribute : Registry.ATTRIBUTE) {
            AttributeInstance attributeInstance = player.getAttribute(attribute);

            if (attributeInstance != null) {
                attributeInstance.getModifiers().stream()
                        .filter(modifier -> modifier.getName().contains("accessory"))
                        .forEach(attributeInstance::removeModifier);
            }
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (player instanceof ServerPlayer)
        {
            SimpleContainer container = new SimpleContainer(5);
            ContainerUtils.loadContainerFromPlayer(container, player, "accessories");

            if (!ContainerUtils.playerHasAccessory(player, itemStack.getItem()))
            {
                for (int i = 0; i < container.getContainerSize(); i++) {
                    if (container.getItem(i).isEmpty()) {
                        container.setItem(i, new ItemStack(itemStack.getItem()));
                        itemStack.shrink(1);
                        container.setChanged();
                    }
                }
                ContainerUtils.saveAccessoriesToPlayer(container, player);
                return InteractionResultHolder.success(itemStack);
            }
        }
        return InteractionResultHolder.pass(itemStack);
    }
}
