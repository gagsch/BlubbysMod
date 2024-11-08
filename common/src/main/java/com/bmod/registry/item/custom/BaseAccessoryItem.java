package com.bmod.registry.item.custom;

import com.bmod.util.ContainerUtils;
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

public class BaseAccessoryItem extends ToolTipItem implements IAccessoryItem {
    private static final UUID ACCESSORY_UUID = new UUID(789, 1337);

    public BaseAccessoryItem() {
        super(DEFAULT_ACCESSORY_PROPERTIES);
    }

    @Override
    public void accessoryTick(ServerLevel level, ServerPlayer player) {

    }

    public static void setAttribute(ServerPlayer player, Attribute attribute, double value, AttributeModifier.Operation operation)
    {
        AttributeInstance attributeInstance = player.getAttribute(attribute);

        if (attributeInstance != null) {
            AttributeModifier existingModifier = attributeInstance.getModifier(ACCESSORY_UUID);

            if (existingModifier != null) {
                if (existingModifier.getAmount() == value && existingModifier.getOperation() == operation) {
                    return;
                } else {
                    attributeInstance.removeModifier(existingModifier);
                }
            }

            AttributeModifier newModifier = new AttributeModifier(ACCESSORY_UUID, "accessory", value, operation);
            attributeInstance.addPermanentModifier(newModifier);
        }
    }

    public static void clearAttributes(ServerPlayer player)
    {
        for (Attribute attribute : Registry.ATTRIBUTE) {
            AttributeInstance attributeInstance = player.getAttribute(attribute);

            if (attributeInstance != null) {
                attributeInstance.getModifiers().stream()
                        .filter(modifier -> "accessory".equals(modifier.getName()))
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

            if (!ContainerUtils.playerAccessoriesHasItem(player, itemStack.getItem()))
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
