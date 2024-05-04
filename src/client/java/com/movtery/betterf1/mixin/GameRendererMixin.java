package com.movtery.betterf1.mixin;

import com.movtery.betterf1.client.BetterF1Client;
import com.movtery.betterf1.client.HUDState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GameRenderer.class)
public class GameRendererMixin {
    @Shadow
    @Final
    MinecraftClient client;

    // Doing it this way is for Optifine compatibility
    @Inject(method = "renderWorld", at = @At("HEAD"))
    private void onRenderHand1(float tickDelta, long limitTime, CallbackInfo ci) {
        if (BetterF1Client.state.equals(HUDState.NO_HUD)) {
            client.options.hudHidden = false;
        }
    }

    @Inject(method = "renderWorld", at = @At("TAIL"))
    private void onRenderHand2(CallbackInfo ci) {
        if (BetterF1Client.state.equals(HUDState.NO_HUD)) {
            client.options.hudHidden = true;
        }
    }
}