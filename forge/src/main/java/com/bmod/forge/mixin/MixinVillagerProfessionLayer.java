package com.bmod.forge.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.VillagerProfessionLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerProfessionLayer.class)
public abstract class MixinVillagerProfessionLayer {
    @Unique
    private static final ResourceLocation MINTSOCKS_LOCATION = new ResourceLocation("textures/entity/villager/mintsocks.png");
    @Unique
    private static final ResourceLocation TRELTA_LOCATION = new ResourceLocation("textures/entity/villager/trelta.png");
    @Unique
    private static final ResourceLocation BLUBBY_LOCATION = new ResourceLocation("textures/entity/villager/3ass.png");

    @Unique
    String blubbysmod$entityName;

    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/Entity;FFFFFF)V", at = @At("HEAD"))
    public void getResourceLocation(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, Entity livingEntity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        this.blubbysmod$entityName = ChatFormatting.stripFormatting(livingEntity.getName().getString());
    }

    @Inject(method = "getResourceLocation", at = @At("HEAD"), cancellable = true)
    public void getResourceLocation(String string, ResourceLocation resourceLocation, CallbackInfoReturnable<ResourceLocation> cir) {
        assert blubbysmod$entityName != null;
        if (blubbysmod$entityName.equalsIgnoreCase("mintsocks_")) {
            cir.setReturnValue(MINTSOCKS_LOCATION);
        } else if (blubbysmod$entityName.equalsIgnoreCase("trelta")) {
            cir.setReturnValue(TRELTA_LOCATION);
        } else if (blubbysmod$entityName.equalsIgnoreCase("3ass")) {
            cir.setReturnValue(BLUBBY_LOCATION);
        }
    }
}
