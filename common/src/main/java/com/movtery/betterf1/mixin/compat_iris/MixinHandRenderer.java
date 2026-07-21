package com.movtery.betterf1.mixin.compat_iris;

import com.movtery.betterf1.BetterF1;
import com.movtery.betterf1.client.HUDState;
import net.irisshaders.iris.pathways.HandRenderer;
import net.minecraft.client.gui.Hud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Pseudo
@Mixin(HandRenderer.class)
public class MixinHandRenderer {

    @Redirect(
            method = "canRender",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/Hud;isHidden()Z"),
            remap = false
    )
    private boolean canRender(Hud instance) {
        return instance.isHidden() && BetterF1.state.equals(HUDState.ALL_HIDDEN);
    }
}