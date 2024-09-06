package me.blubby.bmod.common.item.custom;

import me.blubby.bmod.common.entity.ModEntities;
import me.blubby.bmod.common.entity.custom.DimensionTeleporterEntity;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class DimensionItem extends ToolTipItem {
    public DimensionItem(Item.Properties properties) {
        super(properties, ToolTips.rift_key);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.getCooldowns().addCooldown(this, 200);
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            double radians = Math.toRadians(serverPlayer.getYRot());

            double offsetX = -Math.sin(radians) * 3;
            double offsetZ = Math.cos(radians) * 3;

            double newX = serverPlayer.getX() + offsetX;
            double newY = serverPlayer.getY();
            double newZ = serverPlayer.getZ() + offsetZ;

            Vec3 pos = new Vec3(newX, newY, newZ);

            DimensionTeleporterEntity entity = new DimensionTeleporterEntity(ModEntities.DIMENSION_TELEPORTER.get(), serverPlayer.level);
            entity.moveTo(pos.x, pos.y, pos.z);
            entity.lookAt(EntityAnchorArgument.Anchor.EYES, player.position());

            serverPlayer.level.addFreshEntity(entity);
        }
        return super.use(level, player, hand);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }
}
