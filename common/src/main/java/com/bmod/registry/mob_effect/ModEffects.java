package com.bmod.registry.mob_effect;

import com.bmod.BlubbysMod;
import com.bmod.registry.mob_effect.custom.MycosisEffect;
import com.bmod.registry.mob_effect.custom.TimerEffect;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffect;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.MOB_EFFECT_REGISTRY);

    public static final RegistrySupplier<MobEffect> MYCOSIS = MOB_EFFECTS.register("mycosis", () -> new MycosisEffect(){});
    public static final RegistrySupplier<MobEffect> TIMER = MOB_EFFECTS.register("timer", () -> new TimerEffect(){});
}
