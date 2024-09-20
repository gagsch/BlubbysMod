package com.bmod.util.dlUtils;

import com.bmod.registry.world.ModDimensions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.dimension.DimensionType;

public class DimLightSystem {
    public static boolean enabled = false;
    private static final float[][] LUMINANCE = new float[16][16];

    public static int darken(int c, int blockIndex, int skyIndex) {
        final float lTarget = LUMINANCE[blockIndex][skyIndex];
        final float r = (c & 0xFF) / 255f;
        final float g = ((c >> 8) & 0xFF) / 255f;
        final float b = ((c >> 16) & 0xFF) / 255f;
        final float l = luminance(r, g, b);
        final float f = l > 0 ? Math.min(1, lTarget / l) : 0;

        return f == 1f ? c : 0xFF000000 | Math.round(f * r * 255) | (Math.round(f * g * 255) << 8) | (Math.round(f * b * 255) << 16);
    }

    public static float luminance(float r, float g, float b) {
        return r * 0.2126f + g * 0.7152f + b * 0.0722f;
    }

    public static void updateLuminance(float tickDelta, Minecraft client, GameRenderer worldRenderer, float prevFlicker) {
        final ClientLevel world = client.level;

        if (world != null) {
            if (!(world.dimension() == ModDimensions.BLYDIM_KEY) || client.player.hasEffect(MobEffects.NIGHT_VISION) || (client.player.hasEffect(MobEffects.CONDUIT_POWER) && client.player.getWaterVision() > 0) || world.getSkyFlashTime() > 0) {
                enabled = false;
                return;
            } else {
                enabled = true;
            }

            final DimensionType dim = world.dimensionType();
            final boolean blockAmbient = !(world.dimension() == ModDimensions.BLYDIM_KEY);

            for (int skyIndex = 0; skyIndex < 16; ++skyIndex) {
                if (worldRenderer.getDarkenWorldAmount(tickDelta) > 0.0F) {
                    final float skyDarkness = worldRenderer.getDarkenWorldAmount(tickDelta);
                }

                for (int blockIndex = 0; blockIndex < 16; ++blockIndex) {
                    float blockFactor = 1f;

                    if (!blockAmbient) {
                        blockFactor = 1f - blockIndex / 15f;
                        blockFactor = 1 - blockFactor * blockFactor * blockFactor * blockFactor;
                    }

                    final float blockBase = blockFactor * LightTexture.getBrightness(dim, blockIndex) * (prevFlicker * 0.1F + 1.5F);
                    final float blockGreen = blockBase * ((blockBase * (1)) * (1));
                    final float blockBlue = blockBase * (blockBase * blockBase * (1));

                    float red = blockBase;
                    float green = blockGreen;
                    float blue = blockBlue;

                    final float f = Math.max(0, blockFactor);
                    red = red * (0.99F);
                    green = green * (0.99F);
                    blue = blue * (0.99F);

                    if (red > 1.0F) {
                        red = 1.0F;
                    }

                    if (green > 1.0F) {
                        green = 1.0F;
                    }

                    if (blue > 1.0F) {
                        blue = 1.0F;
                    }

                    final float gamma = client.options.gamma().get().floatValue() * f;
                    float invRed = 1.0F - red;
                    float invGreen = 1.0F - green;
                    float invBlue = 1.0F - blue;
                    invRed = 1.0F - invRed * invRed * invRed * invRed;
                    invGreen = 1.0F - invGreen * invGreen * invGreen * invGreen;
                    invBlue = 1.0F - invBlue * invBlue * invBlue * invBlue;
                    red = red * (1.0F - gamma) + invRed * gamma;
                    green = green * (1.0F - gamma) + invGreen * gamma;
                    blue = blue * (1.0F - gamma) + invBlue * gamma;

                    red = red * (0.99F);
                    green = green * (0.99F);
                    blue = blue * (0.99F);

                    if (red > 1.0F) {
                        red = 1.0F;
                    }

                    if (green > 1.0F) {
                        green = 1.0F;
                    }

                    if (blue > 1.0F) {
                        blue = 1.0F;
                    }

                    if (red < 0.0F) {
                        red = 0.0F;
                    }

                    if (green < 0.0F) {
                        green = 0.0F;
                    }

                    if (blue < 0.0F) {
                        blue = 0.0F;
                    }

                    LUMINANCE[blockIndex][skyIndex] = DimLightSystem.luminance(red, green, blue);
                }
            }
        }
    }
}