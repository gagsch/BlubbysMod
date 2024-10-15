package com.bmod.registry.mob_effect;

import com.bmod.BlubbysMod;
import com.bmod.registry.mob_effect.custom.HorrifiedEffect;
import com.bmod.registry.mob_effect.custom.CardiacArrestEffect;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffect;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.MOB_EFFECT_REGISTRY);

    public static final RegistrySupplier<MobEffect> HORRIFIED = MOB_EFFECTS.register("horrified", () -> new HorrifiedEffect(){});

    public static final RegistrySupplier<MobEffect> CARDIAC_ARREST = MOB_EFFECTS.register("cardiac_arrest", () -> new CardiacArrestEffect(){});
}
