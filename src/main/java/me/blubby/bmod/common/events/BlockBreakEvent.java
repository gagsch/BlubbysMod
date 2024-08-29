package me.blubby.bmod.common.events;

import me.blubby.bmod.common.blocks.ModBlocks;
import me.blubby.bmod.common.entity.ModEntities;
import me.blubby.bmod.common.entity.custom.RotFlyEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.Random;

public class BlockBreakEvent {
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!event.getLevel().isClientSide())
        {
            BlockState state = event.getState();
            BlockPos pos = event.getPos();
            Player player = event.getPlayer();

            LootContext.Builder builder = new LootContext.Builder(event.getLevel().getServer().getLevel(player.level.dimension()))
                    .withParameter(LootContextParams.ORIGIN, new Vec3(pos.getX(),pos.getY(),pos.getZ()))
                    .withParameter(LootContextParams.BLOCK_STATE, state)
                    .withParameter(LootContextParams.TOOL, player.getMainHandItem())
                    .withOptionalParameter(LootContextParams.THIS_ENTITY, player);

            List<ItemStack> drops = state.getDrops(builder);

            if (state.is(Tags.Blocks.ORES) && player.hasEffect(MobEffects.LUCK) && !((EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, player.getMainHandItem()) > 0) || (player.isCreative())))
            {
                for (ItemStack drop : drops) {
                    if (!(drop.getItem() instanceof BlockItem))
                    {
                        Block.popResource(player.getLevel(), pos, drop);
                    }
                }
            }

            if (state.getBlock() == ModBlocks.DEAD_TISSUE_BLOCK.get())
            {
                summonRotFlies(pos, player.level, 1, 3);
            }
        }
    }

    public static void summonRotFlies(BlockPos pos, Level world, int minCount, int maxCount) {
        Random random = new Random();
        int numberOfEntities = random.nextInt(maxCount - minCount + 1) + minCount;

        for (int i = 0; i < numberOfEntities; i++) {
            RotFlyEntity entity = new RotFlyEntity(ModEntities.ROT_FLY.get(), world);
            entity.moveTo(pos, 0, 0);
            world.addFreshEntity(entity);
        }
    }
}