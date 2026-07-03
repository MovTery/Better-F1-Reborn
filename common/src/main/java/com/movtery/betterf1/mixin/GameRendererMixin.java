package com.movtery.betterf1.mixin;

import com.movtery.betterf1.BetterF1;
import com.movtery.betterf1.client.HUDState;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.state.OptionsRenderState;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = GameRenderer.class)
public class GameRendererMixin {

    @Redirect(
            method = "renderItemInHand(Lnet/minecraft/client/renderer/state/level/CameraRenderState;FLorg/joml/Matrix4fc;)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/renderer/state/gui/GuiRenderState;isHudHidden:Z"
            )
    )
    private boolean onRenderHand(GuiRenderState instance) {
        return instance.isHudHidden && BetterF1.state.equals(HUDState.ALL_HIDDEN);
    }
}