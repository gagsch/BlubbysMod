package me.blubby.bmod.common.entity.custom;

import me.blubby.bmod.server.world.dimension.ModDimensions;
import me.blubby.bmod.server.world.portal.ModTeleporter;
import me.blubby.bmod.utils.PositionUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

public class DimensionTeleporterEntity extends Mob {
    private static final int COOLDOWN_TICKS = 100;
    private static final Map<Player, Long> portalCooldowns = new HashMap<>();

    public final AnimationState lifeAnimationState = new AnimationState();
    private int lifespanAnimationTimeout = 0;
    private int lifespanTicks = 200;

    public DimensionTeleporterEntity(EntityType<? extends DimensionTeleporterEntity> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
        this.noPhysics = true;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 0D);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level.isClientSide())
        {
            setupAnimationStates();
        }

        if (!this.level.isClientSide())
        {
            if (lifespanTicks <= 0)
            {
                this.discard();
            }
            lifespanTicks--;
        }
    }

    private void setupAnimationStates() {
        if(this.lifespanAnimationTimeout <= 0) {
            this.lifespanAnimationTimeout = 200;
            this.lifeAnimationState.start(this.tickCount);
        } else {
            --this.lifespanAnimationTimeout;
        }
    }

    @Override
    public void playerTouch(Player player) {
        if (!this.level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            long currentTime = this.level.getGameTime();

            if (portalCooldowns.containsKey(player) && currentTime - portalCooldowns.get(player) < COOLDOWN_TICKS) {
                return;
            }

            portalCooldowns.put(player, currentTime);

            ResourceKey<Level> targetDimension;
            BlockPos targetPosition;

            if (serverPlayer.level.dimension() == ModDimensions.BLYDIM_KEY) {
                targetDimension = Level.OVERWORLD;
                targetPosition = PositionUtils.loadPositionFromPlayer(player, false);
                PositionUtils.savePositionToPlayer(player.blockPosition(), player, true);
            } else {
                targetDimension = ModDimensions.BLYDIM_KEY;
                targetPosition = PositionUtils.loadPositionFromPlayer(player, true);
                PositionUtils.savePositionToPlayer(player.blockPosition(), player, false);
            }

            serverPlayer.changeDimension(this.level.getServer().getLevel(targetDimension), new ModTeleporter(targetPosition));

            if (level.players().isEmpty())
            {
                this.discard();
            }
        }
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    protected boolean canRide(Entity entity) {
        return false;
    }

    @Override
    public void push(Entity entity) { }
}