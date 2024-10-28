package com.bmod.registry.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FairyDustParticles extends TextureSheetParticle {
    protected FairyDustParticles(ClientLevel level, double xPos, double yPos, double zPos, SpriteSet spriteSet, double xd, double yd, double zd) {
        super(level, xPos, yPos, zPos, xd, yd, zd);

        this.friction = 0.8f;
        this.gravity = 1f;
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;
        this.quadSize *= 0.85f;
        this.lifetime = 10;
        this.setSpriteFromAge(spriteSet);

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
    }

    @Override
    public int getLightColor(float partialTick) {
        return 0xF000F0;
    }

    @Override
    public void tick() {
        super.tick();
        this.yd -= 0.02;
        fadeOut();
    }

    private void fadeOut() {
        this.alpha = (-(1 / (float) lifetime) * age + 1);
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType particleOptions, ClientLevel level, double x, double y, double z, double dx, double dy, double dz) {
            return new FairyDustParticles(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
