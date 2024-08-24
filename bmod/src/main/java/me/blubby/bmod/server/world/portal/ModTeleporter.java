package me.blubby.bmod.server.world.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class ModTeleporter implements ITeleporter {
    public static BlockPos thisPos = new BlockPos(0,120,0);

    public ModTeleporter(BlockPos pos) {
        thisPos = pos;
    }


    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destinationWorld,
                              float yaw, Function<Boolean, Entity> repositionEntity) {

        repositionEntity.apply(false);
        entity.teleportTo(thisPos.getX(), thisPos.getY(), thisPos.getZ());
        return entity;
    }
}