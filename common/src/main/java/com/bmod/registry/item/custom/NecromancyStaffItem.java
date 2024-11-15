package com.bmod.registry.item.custom;

import com.bmod.registry.item.ModCreativeTab;
import com.bmod.registry.mob_effect.ModEffects;
import com.bmod.util.world_util.ScoreboardUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.scores.PlayerTeam;
import org.jetbrains.annotations.NotNull;

public class NecromancyStaffItem extends ToolTipItem {
    public NecromancyStaffItem() {
        super(new Properties().tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                .durability(25));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);

        if (level.isClientSide()) {
            level.playSound(player, player.blockPosition(), SoundEvents.WITHER_BREAK_BLOCK, SoundSource.PLAYERS, 1f, 1f);
            return InteractionResultHolder.sidedSuccess(stack, true);
        }

        PlayerTeam team = ScoreboardUtils.getOrCreateTeam(level, "necromancer");

        level.getScoreboard().addPlayerToTeam(player.getScoreboardName(), team);
        for (int i = 0; i < 3; i++) {
            Zombie zombie = new Zombie(EntityType.ZOMBIE, level) {
                @Override
                protected void addBehaviourGoals() {
                    this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0, false));
                    this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
                    this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[]{ZombifiedPiglin.class}));
                    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Monster.class, true));
                }
            };
            zombie.addEffect(new MobEffectInstance(ModEffects.TIMER.get(), 600, 0, true, false, false));
            zombie.moveTo(player.position());
            level.addFreshEntity(zombie);
            level.getScoreboard().addPlayerToTeam(zombie.getScoreboardName(), team);
        }

        stack.hurtAndBreak(1, player, (p) -> {
            p.broadcastBreakEvent(player.getUsedItemHand());
        });
        player.getCooldowns().addCooldown(this, 30);

        return InteractionResultHolder.success(stack);
    }
}
