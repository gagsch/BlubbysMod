package com.bmod.util.mixinUtils;

import com.bmod.registry.world.ModDimensions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.effect.MobEffects;

import java.util.Objects;

public class DimLightSystem {
    public static boolean enabled = false;
    private static final float[][] LUMINANCE = new float[16][16];

    public static int darken(int c, int blockPixelIndex, int skyIndex) {
        // this is the whole function in one line:
        // return ((((c & 0xFF) / 255f) * 0.2126f + (((c >> 8) & 0xFF) / 255f) * 0.7152f + (((c >> 16) & 0xFF) / 255f) * 0.0722f) > 0 ? Math.min(1, LUMINANCE[blockIndex][skyIndex] / (((c & 0xFF) / 255f) * 0.2126f + (((c >> 8) & 0xFF) / 255f) * 0.7152f + (((c >> 16) & 0xFF) / 255f) * 0.0722f)) : 0) == 1f ? c : 0xFF000000 | Math.round(((((c & 0xFF) / 255f) * 0.2126f + (((c >> 8) & 0xFF) / 255f) * 0.7152f + (((c >> 16) & 0xFF) / 255f) * 0.0722f) > 0 ? Math.min(1, LUMINANCE[blockIndex][skyIndex] / (((c & 0xFF) / 255f) * 0.2126f + (((c >> 8) & 0xFF) / 255f) * 0.7152f + (((c >> 16) & 0xFF) / 255f) * 0.0722f)) : 0) * ((c & 0xFF) / 255f) * 255) | (Math.round(((((c & 0xFF) / 255f) * 0.2126f + (((c >> 8) & 0xFF) / 255f) * 0.7152f + (((c >> 16) & 0xFF) / 255f) * 0.0722f) > 0 ? Math.min(1, LUMINANCE[blockIndex][skyIndex] / (((c & 0xFF) / 255f) * 0.2126f + (((c >> 8) & 0xFF) / 255f) * 0.7152f + (((c >> 16) & 0xFF) / 255f) * 0.0722f)) : 0) * (((c >> 8) & 0xFF) / 255f) * 255) << 8) | (Math.round(((((c & 0xFF) / 255f) * 0.2126f + (((c >> 8) & 0xFF) / 255f) * 0.7152f + (((c >> 16) & 0xFF) / 255f) * 0.0722f) > 0 ? Math.min(1, LUMINANCE[blockIndex][skyIndex] / (((c & 0xFF) / 255f) * 0.2126f + (((c >> 8) & 0xFF) / 255f) * 0.7152f + (((c >> 16) & 0xFF) / 255f) * 0.0722f)) : 0) * (((c >> 16) & 0xFF) / 255f) * 255) << 16);
        final float lTarget = LUMINANCE[blockPixelIndex][skyIndex];
        final float r = (c & 0xFF) / 255f;
        final float g = ((c >> 8) & 0xFF) / 255f;
        final float b = ((c >> 16) & 0xFF) / 255f;
        final float l = r * 0.2126f + g * 0.7152f + b * 0.0722f;
        final float f = l > 0 ? Math.min(1, lTarget / l) : 0;
        return f == 1f ? c : 0xFF000000 | Math.round(f * r * 255) | (Math.round(f * g * 255) << 8) | (Math.round(f * b * 255) << 16);
    }

    public static void updateLuminance(Minecraft client, float prevFlicker) {
        final ClientLevel world = client.level;

        if (world != null) {
            enabled = !(!(world.dimension() == ModDimensions.BLYDIM_KEY) ||
                    Objects.requireNonNull(client.player).hasEffect(MobEffects.NIGHT_VISION) ||
                    (client.player.hasEffect(MobEffects.CONDUIT_POWER) && client.player.getWaterVision() > 0) ||
                    world.getSkyFlashTime() > 0);
            if (!enabled)
                return;

            for (int skyIndex = 0; skyIndex < 16; ++skyIndex) {
                for (int blockIndex = 0; blockIndex < 16; ++blockIndex) {
                    float blockFactor = 1f;

                    if (world.dimension() == ModDimensions.BLYDIM_KEY) {
                        blockFactor = (float) (1 - Math.pow(1f - blockIndex / 15f, 4));
                    }

                    final float blockBase = Math.max(0, blockFactor * LightTexture.getBrightness(world.dimensionType(), blockIndex) * (prevFlicker * 0.1F + 1.5F));

                    float[] colors = new float[3];
                    colors[0] = Math.min(blockBase * 0.99F, 1.0F);
                    colors[1] = Math.min(blockBase * blockBase * 0.99F, 1.0F);
                    colors[2] = Math.min(blockBase * blockBase * blockBase * 0.99F, 1.0F);

                    final float f = client.options.gamma().get().floatValue() * blockBase;
                    for (int i = 0; i < 3; i++) {
                        float invColor = 1.0F - colors[i];
                        invColor = (float) (1.0F - Math.pow(invColor, 4));

                        colors[i] = colors[i] * (1.0F - f) + invColor * f;
                        colors[i] = Math.max(0, Math.min(1, colors[i] * 0.99F));
                    }

                    LUMINANCE[blockIndex][skyIndex] = colors[0] * 0.2126f + colors[1] * 0.7152f + colors[2] * 0.0722f;
                }
            }
        }
    }
}