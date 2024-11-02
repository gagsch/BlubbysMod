package com.bmod.registry.item.custom;

import com.bmod.BlubbysMod;
import com.bmod.packet.S2C.S2COpenRecipeBookMessage;
import com.bmod.registry.item.ModCreativeTab;
import com.bmod.util.mixinUtils.IEntityDataSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class AncientRecipeBookItem extends ToolTipItem {
    public AncientRecipeBookItem() {
        super(
                new Properties()
                        .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                        .durability(-1)
                        .stacksTo(1)
        );
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack item = player.getItemInHand(interactionHand);

        if (player instanceof ServerPlayer serverPlayer) {
            CompoundTag playerData = ((IEntityDataSaver) player).blubbysmod$getPersistentData();
            int pagesDiscovered = playerData.getInt("ancient_recipes_unlocked");

            new S2COpenRecipeBookMessage(pagesDiscovered).sendTo(serverPlayer);

            return InteractionResultHolder.success(item);
        }

        return InteractionResultHolder.fail(item);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean bl) {
        if (entity instanceof ServerPlayer serverPlayer)
        {
            CompoundTag playerData = ((IEntityDataSaver) serverPlayer).blubbysmod$getPersistentData();
            int pagesDiscovered = playerData.getInt("ancient_recipes_unlocked");

            stack.getOrCreateTag().putInt(BlubbysMod.MOD_ID + ":pages", pagesDiscovered);
        }
    }
}
