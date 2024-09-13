package me.blubby.bmod.common.blocks.custom;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.blocks.ModBlocks;
import me.blubby.bmod.common.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BubbleBlock extends GlassBlock {

    public BubbleBlock(Properties pProperties) {
        super(pProperties);
    }

    static Minecraft mc = Minecraft.getInstance();
    private static final ResourceLocation IMG_LOCATION = new ResourceLocation(Blubby_sModOfDoom.MOD_ID,"textures/block/bubble_block.png");

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        if (shouldIgnoreSelection(context)) {
            return Shapes.empty();
        }
        return super.getShape(state, world, pos, context);
    }

    private boolean shouldIgnoreSelection(CollisionContext context) {
        if (context instanceof EntityCollisionContext entityContext) {
            Entity entity = entityContext.getEntity();
            if (entity instanceof Player player) {
                if (player.getMainHandItem().getItem() != ModItems.BUBBLE_WAND.get() && player.getOffhandItem().getItem() != ModItems.BUBBLE_WAND.get())
                {
                    return player.level.getBlockState(player.blockPosition().above()) == ModBlocks.BUBBLE_BLOCK.get().defaultBlockState();
                }
            }
        }
        return false;
    }
}
