package com.bmod.util.mixinUtils;

import com.bmod.registry.world.ModDimensions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.effect.MobEffects;

import java.util.Objects;

public class DimLightSystem {
    public static boolean enabled = false;
    private static final float[] LUMINANCE = new float[16];

    public static int darken(int c, int blockPixelIndex) {
        // this is the whole function in one line:
        // return Math.pow((((c & 0xFF) / 255f) * 0.2126f + (((c >> 8) & 0xFF) / 255f) * 0.7152f + (((c >> 16) & 0xFF) / 255f) * 0.0722f) > 0 ? Math.min(1, LUMINANCE[blockPixelIndex] / (((c & 0xFF) / 255f) * 0.2126f + (((c >> 8) & 0xFF) / 255f) * 0.7152f + (((c >> 16) & 0xFF) / 255f) * 0.0722f)) : 0, 0.25) == 1f ? c : (int) (0xFF000000 | Math.round(Math.pow((((c & 0xFF) / 255f) * 0.2126f + (((c >> 8) & 0xFF) / 255f) * 0.7152f + (((c >> 16) & 0xFF) / 255f) * 0.0722f) > 0 ? Math.min(1, LUMINANCE[blockPixelIndex] / (((c & 0xFF) / 255f) * 0.2126f + (((c >> 8) & 0xFF) / 255f) * 0.7152f + (((c >> 16) & 0xFF) / 255f) * 0.0722f)) : 0, 0.25) * ((c & 0xFF) / 255f) * 255) | (Math.round(Math.pow((((c & 0xFF) / 255f) * 0.2126f + (((c >> 8) & 0xFF) / 255f) * 0.7152f + (((c >> 16) & 0xFF) / 255f) * 0.0722f) > 0 ? Math.min(1, LUMINANCE[blockPixelIndex] / (((c & 0xFF) / 255f) * 0.2126f + (((c >> 8) & 0xFF) / 255f) * 0.7152f + (((c >> 16) & 0xFF) / 255f) * 0.0722f)) : 0, 0.25) * (((c >> 8) & 0xFF) / 255f) * 255) << 8) | (Math.round(Math.pow((((c & 0xFF) / 255f) * 0.2126f + (((c >> 8) & 0xFF) / 255f) * 0.7152f + (((c >> 16) & 0xFF) / 255f) * 0.0722f) > 0 ? Math.min(1, LUMINANCE[blockPixelIndex] / (((c & 0xFF) / 255f) * 0.2126f + (((c >> 8) & 0xFF) / 255f) * 0.7152f + (((c >> 16) & 0xFF) / 255f) * 0.0722f)) : 0, 0.25) * (((c >> 16) & 0xFF) / 255f) * 255) << 16));

        final float lTarget = LUMINANCE[blockPixelIndex];

        final float red = (c & 0xFF) / 255f;
        final float green = ((c >> 8) & 0xFF) / 255f;
        final float blue = ((c >> 16) & 0xFF) / 255f;

        final float light = red * 0.2126f + green * 0.7152f + blue * 0.0722f;
        float adjustment = light > 0 ? Math.min(1, lTarget / light) : 0;

        adjustment = (float) Math.pow(adjustment, 0.25);

        return adjustment == 1f ? c : 0xFF000000 | Math.round(adjustment * red * 255) | (Math.round(adjustment * green * 255) << 8) | (Math.round(adjustment * blue * 255) << 16);
    }

    public static void updateLuminance(Minecraft minecraft, float prevFlicker) {
        final ClientLevel world = minecraft.level;

        if (world != null) {
            enabled = !(!(world.dimension() == ModDimensions.BLYDIM_KEY) ||
                    Objects.requireNonNull(minecraft.player).hasEffect(MobEffects.NIGHT_VISION) ||
                    (minecraft.player.hasEffect(MobEffects.CONDUIT_POWER) && minecraft.player.getWaterVision() > 0) ||
                    world.getSkyFlashTime() > 0);
            if (!enabled)
                return;

            for (int blockIndex = 0; blockIndex < 16; ++blockIndex) {
                final float blockBase = (float) Math.max(0, (1 - Math.pow(1f - blockIndex / 15f, 4)) * LightTexture.getBrightness(world.dimensionType(), blockIndex) * (prevFlicker * 0.1F + 1.5F));

                float[] colors = new float[3];
                colors[0] = Math.min(blockBase * 0.99F, 1.0F);
                colors[1] = Math.min(blockBase * blockBase * 0.99F, 1.0F);
                colors[2] = Math.min(blockBase * blockBase * blockBase * 0.99F, 1.0F);

                final float f = minecraft.options.gamma().get().floatValue() * blockBase * 1.8f;
                for (int i = 0; i < 3; i++) {
                    float invColor = 1.0F - colors[i];
                    invColor = (float) (1.0F - Math.pow(invColor, 4));

                    colors[i] = colors[i] * (1.0F - f) + invColor * f;
                    colors[i] = Math.max(0, Math.min(1, colors[i] * 0.99F));
                }

                LUMINANCE[blockIndex] = colors[0] * 0.2126f + colors[1] * 0.7152f + colors[2] * 0.0722f;
            }
        }
    }
}