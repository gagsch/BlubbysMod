package com.bmod.registry.particle;

import com.bmod.BlubbysMod;
import com.bmod.registry.particle.custom.FairyDustParticles;
import com.bmod.registry.particle.custom.SporeParticles;
import dev.architectury.platform.Platform;
import dev.architectury.registry.client.particle.ParticleProviderRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.architectury.utils.Env;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.PARTICLE_TYPE_REGISTRY);

    public static final RegistrySupplier<SimpleParticleType>
            FAIRY_DUST_PARTICLE = PARTICLE_TYPES.register("fairy_dust_particles", () ->
            new SimpleParticleType(false) {}),

            SPORE_PARTICLE = PARTICLE_TYPES.register("spore_particles", () ->
            new SimpleParticleType(false) {});

    public static void initialize() {
        PARTICLE_TYPES.register();
        if (Platform.getEnvironment() == Env.CLIENT) {
            ParticleProviderRegistry.register(FAIRY_DUST_PARTICLE, FairyDustParticles.Provider::new);
            ParticleProviderRegistry.register(SPORE_PARTICLE, SporeParticles.Provider::new);
        }
    }
}
