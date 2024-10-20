package com.bmod.event;

import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.entity.ModEntityTypes;
import com.bmod.registry.entity.custom.RotFlyEntity;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.BlockEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class BlockBreakEvent {

    public static void initialize() {
        BlockEvent.BREAK.register((world, pos, state, player, xp) -> {
            if (!player.getLevel().isClientSide())
            {

                LootContext.Builder builder = new LootContext.Builder(Objects.requireNonNull(Objects.requireNonNull(player.getServer()).getLevel(player.level.dimension())))
                        .withParameter(LootContextParams.ORIGIN, new Vec3(pos.getX(),pos.getY(),pos.getZ()))
                        .withParameter(LootContextParams.BLOCK_STATE, state)
                        .withParameter(LootContextParams.TOOL, player.getMainHandItem())
                        .withOptionalParameter(LootContextParams.THIS_ENTITY, player);

                List<ItemStack> drops = state.getDrops(builder);

                if (Objects.requireNonNull(state.getBlock().arch$registryName()).getPath().contains("ore") && player.hasEffect(MobEffects.LUCK) && !((EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, player.getMainHandItem()) > 0) || (player.isCreative())))
                {
                    for (ItemStack drop : drops) {
                        if (!(drop.getItem() instanceof BlockItem))
                        {
                            Block.popResource(player.getLevel(), pos, drop);
                        }
                    }
                }

                if (state.getBlock() == ModBlocks.ROT_BLOCK.get())
                {
                    summonRotFlies(pos, player.level, 1, 3);
                }
            }

            return EventResult.pass();
        });
    }

    public static void summonRotFlies(BlockPos pos, Level world, int minCount, int maxCount) {
        Random random = new Random();
        int numberOfEntities = random.nextInt(maxCount - minCount + 1) + minCount;

        for (int i = 0; i < numberOfEntities; i++) {
            RotFlyEntity entity = new RotFlyEntity(ModEntityTypes.ROT_FLY.get(), world);
            entity.moveTo(pos, 0, 0);
            world.addFreshEntity(entity);
        }
    }
}
