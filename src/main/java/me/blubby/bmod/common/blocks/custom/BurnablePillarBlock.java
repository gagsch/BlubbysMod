package me.blubby.bmod.common.blocks.custom;

import me.blubby.bmod.utils.WoodUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;

import javax.annotation.Nullable;

import static me.blubby.bmod.utils.WoodUtils.*;

public class BurnablePillarBlock extends RotatedPillarBlock {
    public BurnablePillarBlock(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction)
    {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction)
    {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction)
    {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction action, boolean simulate)
    {
        if(context.getItemInHand().getItem() instanceof AxeItem)
        {
            if(state.is(log(COSMIC_OAK).get()))
            {
               return strippedLog(COSMIC_OAK).get().defaultBlockState().setValue(AXIS,state.getValue(AXIS));
            }
            if(state.is(wood(COSMIC_OAK).get()))
            {
                return strippedWood(COSMIC_OAK).get().defaultBlockState().setValue(AXIS,state.getValue(AXIS));
            }

            if(state.is(log(EBON).get()))
            {
                return strippedLog(EBON).get().defaultBlockState().setValue(AXIS,state.getValue(AXIS));
            }
            if(state.is(wood(EBON).get()))
            {
                return strippedWood(EBON).get().defaultBlockState().setValue(AXIS,state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, context, action, simulate);
    }
}
