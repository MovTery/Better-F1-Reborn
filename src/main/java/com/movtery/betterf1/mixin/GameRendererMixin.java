package com.movtery.betterf1.mixin;

import com.movtery.betterf1.BetterF1;
import com.movtery.betterf1.client.HUDState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GameRenderer.class)
public class GameRendererMixin {

    @Shadow @Final
    Minecraft minecraft;

    // Doing it this way is for Optifine compatibility
    @Inject(method = "renderLevel", at = @At("HEAD"))
    private void onRenderHand1(float pPartialTick, long pNanoTime, CallbackInfo ci) {
        if (BetterF1.state.equals(HUDState.NO_HUD)) {
            minecraft.options.hideGui = false;
        }
    }

    @Inject(method = "renderLevel", at = @At("TAIL"))
    private void onRenderHand2(CallbackInfo ci) {
        if (BetterF1.state.equals(HUDState.NO_HUD)) {
            minecraft.options.hideGui = true;
        }
    }
}