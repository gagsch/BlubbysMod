package com.bmod.registry.item.custom;

import com.bmod.packet.Server2ClientIntMessage;
import com.bmod.registry.item.ModCreativeTab;
import com.bmod.util.mixinUtils.IEntityDataSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class AncientRecipeBookItem extends InventoryItem {
    public AncientRecipeBookItem() {
        super(
                new Properties()
                        .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                        .durability(-1)
                        .stacksTo(1),
                inventoryItems.ANCIENT_RECIPE_BOOK,
                tooltips.ANCIENT_RECIPE_BOOK
        );
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack item = player.getItemInHand(interactionHand);

        if (player instanceof ServerPlayer serverPlayer) {
            CompoundTag playerData = ((IEntityDataSaver) player).blubbysmod$getPersistentData();
            int pagesDiscovered = playerData.getInt("ancient_recipes_unlocked");

            new Server2ClientIntMessage(pagesDiscovered).sendTo(serverPlayer);

            return InteractionResultHolder.success(item);
        }

        return InteractionResultHolder.fail(item);
    }
}
