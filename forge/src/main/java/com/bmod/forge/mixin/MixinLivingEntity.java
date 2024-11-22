package com.bmod.forge.mixin;

import com.bmod.registry.attribute.ModAttributes;
import com.bmod.registry.enchantment.ModEnchantments;
import com.bmod.registry.item.ModItems;
import com.bmod.registry.item.custom.AccessoryItem;
import com.bmod.util.ContainerUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Shadow
    private Optional<BlockPos> lastClimbablePos;

    @Shadow
    public abstract float getMaxHealth();

    @ModifyVariable(method = "hurt", at = @At("HEAD"), index = 2, argsOnly = true)
    private float modifyDamage(float damage, DamageSource damageSource) {
        if (damageSource.getEntity() instanceof ServerPlayer player) {
            Item item = player.getItemInHand(player.getUsedItemHand()).getItem();

            if (item == ModItems.VOLCANIC_MACE.get()) {
                Vec3 speed = player.getDeltaMovement();

                double speedAbs = Math.abs(speed.x) + Math.abs(speed.y) + Math.abs(speed.z);
                double mult = Math.max(Math.pow(speedAbs * 2, 1.3f), 1);

                damage *= mult;

                player.resetFallDistance();
            }
            else if (item == ModItems.REAVER_FANG.get()) {
                damage += this.getMaxHealth() * 0.03f;
            }

        }
        return damage;
    }

    @ModifyArg(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;moveRelative(FLnet/minecraft/world/phys/Vec3;)V", ordinal = 0))
    public float travel(float g) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (livingEntity instanceof Player)
        {
            AttributeInstance swimSpeed = livingEntity.getAttribute(ModAttributes.SWIMMING_SPEED.get());
            if (swimSpeed == null) {
                return g;
            }
            else {
                if (swimSpeed.getBaseValue() != g) {
                    swimSpeed.setBaseValue(g);
                }
                return (float) swimSpeed.getValue();
            }
        }
        return g;
    }

    @Inject(method = "rideableUnderWater", at = @At("RETURN"), cancellable = true)
    public void rideableUnderWater(CallbackInfoReturnable<Boolean> cir)
    {
        if ((Object) this instanceof Player) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci)
    {
        if ((Object) this instanceof Player player)
        {
            SimpleContainer container = new SimpleContainer(5);
            ContainerUtils.loadContainerFromPlayer(container, player, "accessories");

            for (int i = 0; i < container.getContainerSize(); i++) {
                if (container.getItem(i).getItem() instanceof AccessoryItem accessoryItem) {
                    if (player instanceof ServerPlayer serverPlayer) {
                        accessoryItem.serverAccessoryTick(serverPlayer.getLevel(), serverPlayer);
                    }
                    else {
                        accessoryItem.localAccessoryTick(player.getLevel(), player);
                    }
                }
            }
        }
    }

    @Inject(method = "canBreatheUnderwater", at = @At("RETURN"), cancellable = true)
    public void canBreatheUnderwater(CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof Player player) {
            if (ContainerUtils.playerHasAccessory(player, ModItems.OXYGEN_TANK.get())) {
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(method = "onClimbable", at = @At("TAIL"), cancellable = true)
    public void onClimbable(CallbackInfoReturnable<Boolean> cir)
    {
        if ((Object) this instanceof Player player)
        {
            if (player.getFeetBlockState().is(Blocks.COBWEB))
            {
                ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

                if (!boots.isEmpty()) {
                    int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.WEB_WALKER.get(), boots);

                    if (enchantmentLevel == 3) {
                        this.lastClimbablePos = Optional.of(player.blockPosition());

                        cir.setReturnValue(true);
                    }
                }
            }
        }
    }
}
