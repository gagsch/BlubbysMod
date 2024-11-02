package com.bmod.registry.item.custom;

import com.bmod.BlubbysMod;
import com.bmod.registry.item.ModCreativeTab;
import com.bmod.registry.recipe.EnrichmentRecipe;
import com.bmod.util.mixinUtils.IEntityDataSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class AncientRecipePageItem extends ToolTipItem {
    public AncientRecipePageItem() {
        super(
                new Properties()
                        .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                        .durability(-1)
                        .stacksTo(64)
        );
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack item = player.getItemInHand(interactionHand);
        if (player instanceof ServerPlayer serverPlayer) {
            CompoundTag playerData = ((IEntityDataSaver) player).blubbysmod$getPersistentData();

            if (playerData.getInt("ancient_recipes_unlocked") > level.getRecipeManager().getAllRecipesFor(EnrichmentRecipe.Type.INSTANCE).size() - 1)
            {
                serverPlayer.displayClientMessage(Component.literal("You have all the pages!"), true);
                return InteractionResultHolder.fail(item);
            }

            playerData.putInt("ancient_recipes_unlocked", playerData.getInt("ancient_recipes_unlocked") + 1);

            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.VILLAGER_WORK_LIBRARIAN, SoundSource.PLAYERS,
                    1.0F, 1.0F);
            item.shrink(1);
            return InteractionResultHolder.consume(item);
        }
        return InteractionResultHolder.pass(item);
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
