package com.bmod.registry.mob_effect.custom;

import com.bmod.BlubbysMod;
import com.bmod.registry.item.ModItems;
import com.bmod.registry.mob_effect.ModMobEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Set;

public class CardiacArrestEffect extends MobEffect {

    protected CardiacArrestEffect() {
        super(MobEffectCategory.HARMFUL, 0x551212);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int i) {
        if (livingEntity instanceof ServerPlayer player)
        {
            for (ItemStack item : player.getInventory().items)
            {
                CompoundTag tag = item.getTag();


                if (tag != null) {
                    ServerPlayer linkedPlayer = null;
                    if (tag.hasUUID(BlubbysMod.MOD_ID + ":player_link"))
                    {
                        linkedPlayer = player.server.getPlayerList().getPlayer(tag.getUUID(BlubbysMod.MOD_ID + ":player_link"));
                    }

                    boolean isTheLinkedPlayerFromTheUUIDFromTheTagOfTheCurrentForLoopsItemFromTheCurrentInstanceOfTheCardiacArrestEffectsPlayersInventoryStillANullValueBecauseTheTagDoesNotContainThePLAYER_LINKUUIDOrIsItAValidPlayerFoundInTheServersCurrentPlayerListAndIfItIsAValidPlayerFoundInTheServersCurrentPlayerListThenDoesTheLinkedPlayerFromTheUUIDFromTheTagOfTheCurrentForLoopsItemFromTheCurrentInstanceOfTheCardiacArrestEffectsPlayersInventoryEffectListHaveTheNonNullValueOfTheHorrifiedEffect = (linkedPlayer != null && linkedPlayer.hasEffect(ModMobEffects.HORRIFIED.get())); // Variable is descriptive, good name for a variable I think!

                    if (tag.getInt(BlubbysMod.MOD_ID + ":pin") == 1 || isTheLinkedPlayerFromTheUUIDFromTheTagOfTheCurrentForLoopsItemFromTheCurrentInstanceOfTheCardiacArrestEffectsPlayersInventoryStillANullValueBecauseTheTagDoesNotContainThePLAYER_LINKUUIDOrIsItAValidPlayerFoundInTheServersCurrentPlayerListAndIfItIsAValidPlayerFoundInTheServersCurrentPlayerListThenDoesTheLinkedPlayerFromTheUUIDFromTheTagOfTheCurrentForLoopsItemFromTheCurrentInstanceOfTheCardiacArrestEffectsPlayersInventoryEffectListHaveTheNonNullValueOfTheHorrifiedEffect
                    ) {
                        player.hurt(DamageSource.OUT_OF_WORLD, 3);
                    }
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int i, int j) {
        return true;
    }
}
