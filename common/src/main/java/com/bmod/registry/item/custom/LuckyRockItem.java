package com.bmod.registry.item.custom;

import com.bmod.registry.item.ModCreativeTab;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import org.jetbrains.annotations.NotNull;

public class LuckyRockItem extends ToolTipItem implements IAccessoryItem {

    public LuckyRockItem() {
        super(new Properties()
                .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                .durability(-1)
                .stacksTo(1));
    }

    @Override
    public void accessoryTick(@NotNull ServerLevel level, @NotNull ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.LUCK, 30, 4));
    }
}
